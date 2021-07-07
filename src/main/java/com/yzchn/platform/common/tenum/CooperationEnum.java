package com.yzchn.platform.common.tenum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName CooperationEnum.java
 * @Description TODO 物业合作状态的枚举类
 * @createTime 2021年06月29日 09:39:00
 */
public enum CooperationEnum {
    NORMAL(1,"正常"),

    STOP_COOPERATION(2,"停止");

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String msg;

    CooperationEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
