package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表(TRole)表实体类
 *
 * @author songyuan
 * @since 2021-06-22 16:25:09
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TRole extends Model<TRole> implements Serializable {

    private static final long serialVersionUID = -62786485191685679L;

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
