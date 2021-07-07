package com.yzchn.platform.common.config;

import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.utils.Response;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description TODO 全局异常拦截器
 * @createTime 2021年05月10日 15:04:00
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Map<String, Object> handleException(BusinessException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", exception.getCode());
        map.put("message", exception.getMsg());
        return map;
    }

    /**
     * 参数缺失异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response MethodArgumentNotValidException(ConstraintViolationException e) {
        e.printStackTrace();
        Response response = new Response(500, e.getConstraintViolations().iterator().next().getMessage(), "");
        return response;
    }

}
