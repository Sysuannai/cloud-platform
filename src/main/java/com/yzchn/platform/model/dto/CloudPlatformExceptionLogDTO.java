package com.yzchn.platform.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * (CloudPlatformExceptionLog)参数封装类
 *
 * @author songyuan
 * @since 2021-06-25 10:13:44
 */
@Data()
@EqualsAndHashCode()
public class CloudPlatformExceptionLogDTO {

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

    /**
     * 页
     */
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
