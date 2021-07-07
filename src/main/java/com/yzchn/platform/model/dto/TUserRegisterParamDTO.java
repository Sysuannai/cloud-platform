package com.yzchn.platform.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName TUserRegisterParamDTO.java
 * @Description TODO
 * @createTime 2021年06月22日 10:39:00
 */
@Data
public class TUserRegisterParamDTO {
    private String username;
    private String password;
    private String icon;
    private String nickName;
    private String note;
    private Integer pid;

}
