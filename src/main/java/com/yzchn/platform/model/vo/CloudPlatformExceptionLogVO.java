package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (CloudPlatformExceptionLog)表实体类
 *
 * @author songyuan
 * @since 2021-06-25 10:09:20
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class CloudPlatformExceptionLogVO implements Serializable {

    private static final long serialVersionUID = 975302459580843170L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 请求参数
     */
    private String excRequParam;
    /**
     * 异常名称
     */
    private String excName;
    /**
     * 异常信息
     */
    private String excMessage;
    /**
     * 操作员id
     */
    private Long userId;
    /**
     * 操作员名称
     */
    private String username;
    /**
     * 操作方法
     */
    private String operMethod;
    /**
     * 请求URL
     */
    private String operUrl;
    /**
     * 请求IP
     */
    private String operIp;
    /**
     * 操作版本号
     */
    private String operVersion;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
