package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物业公司账号(TPropertyAccount)表实体类
 *
 * @author songyuan
 * @since 2021-06-29 10:08:22
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TPropertyAccount extends Model<TPropertyAccount> implements Serializable {

    private static final long serialVersionUID = 611386545655022230L;

    /**
     * 账号编号
     */
    private Long accountId;
    /**
     * 物业公司编号
     */
    private Long propertyId;
    /**
     * 员工工号
     */
    private String employeeNo;
    /**
     * 员工电话号码
     */
    private String phone;
    /**
     * 登录密码
     */
    private String passwd;
    /**
     * 昵称
     */
    private String name;
    /**
     * 账号状态 0: 新账号 1：正式员工 2:删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建时IP地址
     */
    private String createIp;
    /**
     * 最后访问时间
     */
    private LocalDateTime lastTime;
    /**
     * 最后访问时IP地址
     */
    private String lastIp;
    /**
     * 登录次数
     */
    private Long loginNum;
    /**
     * 1-系统管理员 2-管理员 3-普通员工
     */
    private Object type;
    /**
     * 备注
     */
    private String remark;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }
}
