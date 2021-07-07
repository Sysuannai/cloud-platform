package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(TRole)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-22 16:26:04
 */
@Mapper
public interface TRoleDao extends BaseMapper<TRole> {

}
