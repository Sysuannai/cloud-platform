package com.yzchn.platform.model.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表(TUser)表实体类
 *
 * @author songyuan
 * @since 2021-06-21 15:57:51
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TUser extends Model<TUser> implements Serializable {

    private static final long serialVersionUID = -59715652974072315L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 账号名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String icon;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 是否是管理员
     */
    private Integer isAdmin;
    /**
     * 备注
     */
    private String note;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;
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
     * 父级Id
     */
    private Long pid;

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
