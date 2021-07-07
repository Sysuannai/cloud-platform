package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TResourceCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源分类表(TResourceCategory)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-23 11:07:26
 */
@Mapper
public interface TResourceCategoryDao extends BaseMapper<TResourceCategory> {

}
