package com.yzchn.platform.common.tenum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName AccountEnum.java
 * @Description TODO
 * @createTime 2021年06月29日 10:12:00
 */
public enum AccountEnum {
    NEW_ACCOUNT(0, "新账号"),
    FULL_TIME_EMPLOYEES(1,"正式员工"),
    DELETE(3,"删除");
    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String msg;

    AccountEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
