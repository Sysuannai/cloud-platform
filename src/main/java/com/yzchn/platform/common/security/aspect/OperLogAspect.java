package com.yzchn.platform.common.security.aspect;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yzchn.platform.common.annotation.OperLog;
import com.yzchn.platform.common.utils.IPUtil;
import com.yzchn.platform.common.wrapper.RequestWrapper;
import com.yzchn.platform.model.dto.CloudPlatformExceptionLogDTO;
import com.yzchn.platform.model.dto.CloudPlatformLoggingDTO;
import com.yzchn.platform.model.entity.CloudPlatformExceptionLog;
import com.yzchn.platform.model.entity.CloudPlatformLogging;
import com.yzchn.platform.service.CloudPlatformExceptionLogService;
import com.yzchn.platform.service.CloudPlatformLoggingService;
import com.yzchn.platform.service.SecurityUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName OperLogAspect.java
 * @Description TODO 异常信息切面
 * @createTime 2021年06月25日 09:55:00
 */
@Aspect
@Component
public class OperLogAspect {

    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private CloudPlatformLoggingService loggingService;
    @Autowired
    private CloudPlatformExceptionLogService exceptionLogService;
    /**
     * 操作版本号
     */
    @Value("${version}")
    private String operVer;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.yzchn.platform.common.annotation.OperLog)")
    public void operLogPoinCut() {
    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.yzchn.platform.controller..*.*(..))")
    public void operExceptionLogPoinCut() {
    }

    /**
     * @param joinPoint 切入点
     * @param keys      返回结果
     * @return void
     * @author 洛无异
     * @description //TODO 正常返回通知,拦截用户操作日志.连接点正常执行完成后执行,如果连接点异常, 就不抛出异常
     * @date 2021/6/25 10:04
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        CloudPlatformLoggingDTO cloudPlatformLogging = new CloudPlatformLoggingDTO();
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获得当前操作
        OperLog opLog = method.getAnnotation(OperLog.class);
        if (opLog != null) {
            String operModul = opLog.operModul();
            String operType = opLog.operType();
            String operDesc = opLog.operDesc();
            // 操作模块
            cloudPlatformLogging.setOperModul(operModul);
            // 操作类型
            cloudPlatformLogging.setOperType(operType);
            // 操作描述
            cloudPlatformLogging.setOperDesc(operDesc);
        }
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;
        cloudPlatformLogging.setOperMethod(methodName);
        // 请求的参数
        // 将参数所在的数组转换成json
        cloudPlatformLogging.setOperRequParam(getParam(joinPoint, request));
        cloudPlatformLogging.setOperRespParam(JSON.toJSONString(keys));
        cloudPlatformLogging.setUserId(securityUserService.getUserId());
        cloudPlatformLogging.setUsername(securityUserService.getUserName());
        cloudPlatformLogging.setOperIp(IPUtil.getClientIp(request));
        cloudPlatformLogging.setOperUri(request.getRequestURI());
        cloudPlatformLogging.setOperVersion(operVer);
        loggingService.insert(cloudPlatformLogging);
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;
        // 请求的参数
        // 将参数所在的数组转换成json
        CloudPlatformExceptionLogDTO cloudPlatformExceptionLog = new CloudPlatformExceptionLogDTO();
        cloudPlatformExceptionLog.setOperMethod(methodName);
        cloudPlatformExceptionLog.setExcRequParam(getParam(joinPoint, request));
        cloudPlatformExceptionLog.setExcName(e.getClass().getName());
        cloudPlatformExceptionLog.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
        cloudPlatformExceptionLog.setUserId(securityUserService.getUserId());
        cloudPlatformExceptionLog.setUsername(securityUserService.getUserName());
        cloudPlatformExceptionLog.setOperUrl(request.getRequestURI());
        cloudPlatformExceptionLog.setOperIp(IPUtil.getClientIp(request));
        cloudPlatformExceptionLog.setOperVersion(operVer);
        exceptionLogService.insert(cloudPlatformExceptionLog);
    }


    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public String converMap(Map<String, String[]> paramMap) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return new JSONObject(rtnMap).toJSONString();
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

    private static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
        } finally {
        }
        return data.toString();
    }

    public String getParam(JoinPoint joinPoint, HttpServletRequest request) {
        // 判断controller里传入的参数是否为request和response
        if ("GET".equals(request.getMethod())) {
            return converMap(request.getParameterMap());
        }
        if (joinPoint.getArgs()[0] != null) {
            Object[] paramValues = joinPoint.getArgs();
            return ((RequestWrapper) paramValues[0]).getBody();
        }
        return "{}";
    }
}
