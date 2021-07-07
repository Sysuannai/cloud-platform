package com.yzchn.platform.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName LoginParamDto.java
 * @Description TODO 登录的校验类
 * @createTime 2021年06月21日 17:17:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginParamDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
