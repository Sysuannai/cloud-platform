package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表(TRole)表实体类
 *
 * @author songyuan
 * @since 2021-06-22 16:25:10
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TRoleVO implements Serializable {

    private static final long serialVersionUID = 175485453405494642L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
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

    private String untitled;

}
