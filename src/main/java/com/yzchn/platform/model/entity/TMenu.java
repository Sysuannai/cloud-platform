package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表(TMenu)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:04:14
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TMenu extends Model<TMenu> implements Serializable {

    private static final long serialVersionUID = 533884547180351012L;

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
    /**
     * 逻辑删除0未删除1已删除
     */
    private Integer isDelete;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
