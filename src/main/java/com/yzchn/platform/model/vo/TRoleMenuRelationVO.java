package com.yzchn.platform.model.vo;

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
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TRoleMenuRelationVO implements Serializable {

    private static final long serialVersionUID = -15883483015661969L;

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

}
