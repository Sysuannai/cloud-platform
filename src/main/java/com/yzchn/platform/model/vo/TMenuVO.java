package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import com.yzchn.platform.model.entity.TMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表(TMenu)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:04:15
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TMenuVO extends TMenu implements Serializable {

    private static final long serialVersionUID = 643518235959811889L;
    
    /**
     * 主键id
     */
    private Long id;
    /**
     * 父级id
     */
    private Long parentId;
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
