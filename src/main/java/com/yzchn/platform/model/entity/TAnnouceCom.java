package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公告发布小区表(TAnnouceCom)表实体类
 *
 * @author songyuan
 * @since 2021-06-29 10:56:02
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TAnnouceCom extends Model<TAnnouceCom> implements Serializable {

    private static final long serialVersionUID = 890001521552160692L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 小区公告ID
     */
    private Long annouceId;
    /**
     * 小区ID
     */
    private Long comId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public TAnnouceCom( Long annouceId, Long comId, LocalDateTime createTime) {
        this.id = id;
        this.annouceId = annouceId;
        this.comId = comId;
        this.createTime = createTime;
    }
}
