package com.yzchn.platform.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName BusinessException.java
 * @Description TODO
 * @createTime 2021年05月10日 15:08:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 8204335493386165578L;
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;


    /**
     * 传入BusinessEnum类
     *
     * @param businessEnum
     */
    public BusinessException(BusinessEnum businessEnum) {
        this.code = businessEnum.getCode();
        this.msg = businessEnum.getMsg();
    }

    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }
}