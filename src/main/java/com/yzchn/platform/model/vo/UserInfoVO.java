package com.yzchn.platform.model.vo;

import com.yzchn.platform.model.bo.MenuListBO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName UserInfoVO.java
 * @Description TODO
 * @createTime 2021年06月23日 13:58:00
 */
@Data
@Builder
public class UserInfoVO {
    private String userName;
    private List<MenuListBO> menuList;
    private String icon;
    private List<String> roles;

}
