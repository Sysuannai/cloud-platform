package com.yzchn.platform.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * (CloudPlatformLogging)参数封装类
 *
 * @author songyuan
 * @since 2021-06-25 10:13:45
 */
@Data()
@EqualsAndHashCode()
public class CloudPlatformLoggingDTO {

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
