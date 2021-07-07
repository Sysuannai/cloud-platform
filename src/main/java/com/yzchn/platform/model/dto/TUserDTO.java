package com.yzchn.platform.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yzchn.platform.common.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户表(TUser)参数封装类
 *
 * @author songyuan
 * @since 2021-06-21 16:22:11
 */
@Data()
@EqualsAndHashCode()
public class TUserDTO {

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

    public Integer getPageNum() {
        return StringUtils.isObjectNotEmpty(pageNum) ? pageNum : 0;
    }

    public Integer getPageSize() {
        return StringUtils.isObjectNotEmpty(pageSize) ? pageSize : 10;
    }

}
