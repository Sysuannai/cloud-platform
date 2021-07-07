package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (CloudPlatformLogging)表实体类
 *
 * @author songyuan
 * @since 2021-06-25 10:09:20
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class CloudPlatformLogging extends Model<CloudPlatformLogging> implements Serializable {

    private static final long serialVersionUID = 514124024517652649L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 功能模块
     */
    private String operModul;
    /**
     * 操作类型
     */
    private String operType;
    /**
     * 操作描述
     */
    private String operDesc;
    /**
     * 请求参数
     */
    private String operRequParam;
    /**
     * 返回参数
     */
    private String operRespParam;
    /**
     * 操作人Id
     */
    private Long userId;
    /**
     * 操作人账号
     */
    private String username;
    /**
     * 操作方法
     */
    private String operMethod;
    /**
     * 请求URL
     */
    private String operUri;
    /**
     * 请求IP
     */
    private String operIp;
    /**
     * 请求时间
     */
    private LocalDateTime createTime;
    /**
     * 操作版本号
     */
    private String operVersion;

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
