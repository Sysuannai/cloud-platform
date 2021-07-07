package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源分类表(TResourceCategory)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:08:50
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TResourceCategoryVO implements Serializable {

    private static final long serialVersionUID = 657389643425149671L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
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

}
