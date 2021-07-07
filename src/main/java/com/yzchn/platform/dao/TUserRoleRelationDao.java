package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TUserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联表(TUserRoleRelation)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-23 11:07:30
 */
@Mapper
public interface TUserRoleRelationDao extends BaseMapper<TUserRoleRelation> {

}
