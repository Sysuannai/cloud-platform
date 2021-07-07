package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.CloudPlatformLoggingDao;
import com.yzchn.platform.model.dto.CloudPlatformLoggingDTO;
import com.yzchn.platform.model.entity.CloudPlatformLogging;

import java.util.List;

/**
 * (CloudPlatformLogging)表服务接口
 *
 * @author songyuan
 * @since 2021-06-25 10:09:52
 */
public interface CloudPlatformLoggingService extends IService<CloudPlatformLogging> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-25 10:09:52
     */
    CloudPlatformLogging selectCloudPlatformLoggingById(Long id);

    /**
     * @param cloudPlatformLoggingdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-25 10:09:52
     */
    IPage<CloudPlatformLogging> findCloudPlatformLoggingSelectList(CloudPlatformLoggingDTO cloudPlatformLoggingdto);


    /**
     * @param cloudPlatformLoggingdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-25 10:09:52
     */
    void insert(CloudPlatformLoggingDTO cloudPlatformLoggingdto);

    /**
     * @param cloudPlatformLoggingdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-25 10:09:52
     */
    CloudPlatformLogging update(CloudPlatformLoggingDTO cloudPlatformLoggingdto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-25 10:09:52
     */
    void deleteById(List<Long> idList);
}

