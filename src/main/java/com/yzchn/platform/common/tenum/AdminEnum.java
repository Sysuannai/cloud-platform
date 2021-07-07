package com.yzchn.platform.common.tenum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName AdminEnum.java
 * @Description TODO
 * @createTime 2021年06月22日 11:02:00
 */
public enum AdminEnum {
    ADMINISTRATOR(1, "管理员"),
    GENERAL_USER(0, "普通用户");

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String msg;

    AdminEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
