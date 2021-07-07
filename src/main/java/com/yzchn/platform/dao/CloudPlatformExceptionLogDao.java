package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.CloudPlatformExceptionLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * (CloudPlatformExceptionLog)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-25 10:09:53
 */
@Mapper
public interface CloudPlatformExceptionLogDao extends BaseMapper<CloudPlatformExceptionLog> {

}
