package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TAnnouceCom;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告发布小区表(TAnnouceCom)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-29 10:56:21
 */
@Mapper
public interface TAnnouceComDao extends BaseMapper<TAnnouceCom> {

}
