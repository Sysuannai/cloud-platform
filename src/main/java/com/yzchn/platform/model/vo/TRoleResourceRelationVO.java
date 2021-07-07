package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色资源关联表(TRoleResourceRelation)表实体类
 *
 * @author songyuan
 * @since 2021-06-23 11:08:51
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TRoleResourceRelationVO implements Serializable {

    private static final long serialVersionUID = 861763004697216181L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 资源id
     */
    private Long resourceId;
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
