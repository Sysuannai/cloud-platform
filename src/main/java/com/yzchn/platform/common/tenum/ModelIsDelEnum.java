package com.yzchn.platform.common.tenum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName PModel.java
 * @Description TODO
 * @createTime 2021年05月10日 16:34:00
 */
public enum ModelIsDelEnum {

    EFFECTIVE(0, "有效"),
    INVALID(1, "无效");

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String msg;

    ModelIsDelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
