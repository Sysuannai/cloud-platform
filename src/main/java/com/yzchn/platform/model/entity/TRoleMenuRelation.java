package com.yzchn.platform.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单关联表(TRoleMenuRelation)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:08:49
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TRoleMenuRelation extends Model<TRoleMenuRelation> implements Serializable {

    private static final long serialVersionUID = 311383217264442409L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;

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
