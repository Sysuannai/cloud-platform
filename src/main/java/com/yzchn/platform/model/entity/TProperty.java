package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公司资料 (TProperty)表实体类
 *
 * @author songyuan
 * @since 2021-06-28 11:19:23
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TProperty extends Model<TProperty> implements Serializable {

    private static final long serialVersionUID = -16031949962199108L;

    /**
     * 自增编号
     */
    private Long propertyId;
    /**
     * 物业公司代码
     */
    private String propertyNo;
    /**
     * 物业公司名称
     */
    private String name;
    /**
     * 注册时间
     */
    private LocalDateTime regDate;
    /**
     * 注册地址
     */
    private String regAddress;
    /**
     * 注册资本
     */
    private String regCapital;
    /**
     * 法定代表人
     */
    private String legalPerson;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 公司电话
     */
    private String tel;
    /**
     * 传真
     */
    private String fax;
    /**
     * 公司网址
     */
    private String website;
    /**
     * 电子信箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
    /**
     * 社区编号
     */
    private String communityId;
    /**
     * 合作状态 1->正常 2->停止
     */
    private Integer cooperation;
    /**
     * 数据账号id
     */
    private Long userId;
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
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.propertyId;
    }
}
