package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统资源表(TResource)表实体类
 *
 * @author songyuan
 * @since 2021-06-24 15:55:41
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TResourceVO implements Serializable {

    private static final long serialVersionUID = 131468116680930570L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * 资源分类id
     */
    private Long categoryId;

}
