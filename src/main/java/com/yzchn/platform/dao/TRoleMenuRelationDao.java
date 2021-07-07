package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TRoleMenuRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色菜单关联表(TRoleMenuRelation)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-23 11:07:27
 */
@Mapper
public interface TRoleMenuRelationDao extends BaseMapper<TRoleMenuRelation> {

}
