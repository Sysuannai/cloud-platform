package com.yzchn.platform.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小区公告(TAnnouce)表实体类
 *
 * @author songyuan
 * @since 2021-06-29 10:56:01
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data()
public class TAnnouce extends Model<TAnnouce> implements Serializable {

    private static final long serialVersionUID = 937402862491495781L;

    /**
     * 主键ID
     */
    private Long annouceId;

    private LocalDateTime createTime;

    private LocalDateTime mdyTime;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告内容
     */
    private Object annouceDesc;
    /**
     * 设备端图片地址
     */
    private String dPicUrl;
    /**
     * 移动端图片地址
     */
    private String aPicUrl;
    /**
     * 广告地址
     */
    private String url;
    /**
     * 状态
     */
    private String status;
    /**
     * 上架时间
     */
    private LocalDateTime onTime;
    /**
     * 下架时间
     */
    private LocalDateTime downTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.annouceId;
    }
}
