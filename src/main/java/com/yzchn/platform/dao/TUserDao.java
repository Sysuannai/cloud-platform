package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(TUser)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-21 16:21:08
 */
@Mapper
public interface TUserDao extends BaseMapper<TUser> {

}
