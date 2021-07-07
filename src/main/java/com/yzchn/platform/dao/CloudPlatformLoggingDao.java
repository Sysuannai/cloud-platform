package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.CloudPlatformLogging;
import org.apache.ibatis.annotations.Mapper;

/**
 * (CloudPlatformLogging)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-25 10:09:52
 */
@Mapper
public interface CloudPlatformLoggingDao extends BaseMapper<CloudPlatformLogging> {

}
