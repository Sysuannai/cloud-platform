package com.yzchn.platform.common.utils;

import com.yzchn.platform.common.exception.BusinessEnum;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @description: 接口返回对象
 * @author: BaiLongjin
 * @create: 2020-04-23 14:32
 **/
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private T data;
    private boolean success;

    public Response() {

    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> getInstance(BusinessEnum res) {
        Response<T> instance = new Response();
        instance.setCode(res.getCode());
        instance.setMessage(res.getMsg());
        return instance;
    }

    public static <T> Response<T> ok(BusinessEnum res, T data) {
        Response<T> instance = new Response();
        instance.setCode(res.getCode());
        instance.setMessage(res.getMsg());
        instance.setSuccess(true);
        instance.setData(data);
        return instance;
    }

    public static <T> Response<T> ok(BusinessEnum res) {
        Response<T> instance = new Response();
        instance.setCode(res.getCode());
        instance.setMessage(res.getMsg());
        instance.setSuccess(true);
        return instance;
    }

    public static <T> Response<T> error(BusinessEnum res, T data) {
        Response<T> instance = new Response();
        instance.setCode(res.getCode());
        instance.setMessage(res.getMsg());
        instance.setSuccess(false);
        return instance;
    }

    public static <T> Response<T> error(BusinessEnum res) {
        Response<T> instance = new Response();
        instance.setCode(res.getCode());
        instance.setMessage(res.getMsg());
        instance.setSuccess(false);
        return instance;
    }

    public static <T> Response<T> getInstance(BusinessEnum res, String message) {
        Response<T> instance = new Response();
        instance.setCode(res.getCode());
        instance.setMessage(message);
        return instance;
    }

    public void jsonOut(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        String result = JacksonUtils.serialization(this);
        response.getWriter().print(result);
    }
}
