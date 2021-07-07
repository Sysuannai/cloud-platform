package com.yzchn.platform.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联表(TUserRoleRelation)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:08:51
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TUserRoleRelation extends Model<TUserRoleRelation> implements Serializable {

    private static final long serialVersionUID = -75584473375248379L;

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
