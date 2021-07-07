package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yzchn.platform.model.entity.TResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统资源表(TResource)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-21 16:21:08
 */
@Mapper
public interface TResourceDao extends BaseMapper<TResource> {
    @Select("" +
            "SELECT\n" +
            "            ur.id id,\n" +
            "            ur.created_time createdTime,\n" +
            "            ur.`name` `name`,\n" +
            "            ur.url url,\n" +
            "            ur.description description,\n" +
            "            ur.category_id categoryId\n" +
            "        FROM\n" +
            "            t_user_role_relation ar\n" +
            "        LEFT JOIN t_role r ON ar.role_id = r.id\n" +
            "        LEFT JOIN t_role_resource_relation rrr ON r.id = rrr.role_id\n" +
            "        LEFT JOIN t_resource ur ON ur.id = rrr.resource_id  ${ew.customSqlSegment} ")
    List<TResource> getResourceList(@Param(Constants.WRAPPER) Wrapper queryWrapper);
}
