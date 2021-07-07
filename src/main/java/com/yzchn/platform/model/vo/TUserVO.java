package com.yzchn.platform.model.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import com.yzchn.platform.model.entity.TUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表(TUser)表实体类
 *
 * @author songyuan
 * @since 2021-06-22 10:36:43
 */
@SuppressWarnings("serial")
@Data()
@EqualsAndHashCode()
public class TUserVO extends TUser implements Serializable {
    private static final long serialVersionUID = 697218425287875032L;

}
