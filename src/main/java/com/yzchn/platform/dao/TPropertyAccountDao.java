package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzchn.platform.model.entity.TPropertyAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物业公司账号(TPropertyAccount)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-29 10:08:35
 */
@Mapper
public interface TPropertyAccountDao extends BaseMapper<TPropertyAccount> {

}
