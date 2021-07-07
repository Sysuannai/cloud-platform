package com.yzchn.platform.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 菜单表(TMenu)参数封装类
 *
 * @author songyuan
 * @since 2021-06-23 11:04:14
 */
@Data()
@EqualsAndHashCode()
public class TMenuDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单排序
     */
    private String sort;

    /**
     * 前端显示名称
     */
    private String name;

    /**
     * 前端显示图标
     */
    private String icon;

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

}
