package com.yzchn.platform.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName MenuListBO.java
 * @Description TODO
 * @createTime 2021年06月23日 13:59:00
 */
@Data
public class MenuListBO  implements Serializable {

    private static final long serialVersionUID = 1017728560234573067L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 菜单排序
     */
    private String sort;
    /**
     * 前端显示名称
     */
    private String name;
    /**
     * 前端显示图标
     */
    private String icon;
}
