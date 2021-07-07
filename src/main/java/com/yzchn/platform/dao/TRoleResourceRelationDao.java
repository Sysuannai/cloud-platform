package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TRoleResourceRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色资源关联表(TRoleResourceRelation)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-23 11:07:28
 */
@Mapper
public interface TRoleResourceRelationDao extends BaseMapper<TRoleResourceRelation> {

}
