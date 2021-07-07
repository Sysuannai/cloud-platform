package com.yzchn.platform.model.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联表(TUserRoleRelation)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:08:52
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TUserRoleRelationVO implements Serializable {

    private static final long serialVersionUID = -62688638764800456L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;

}
