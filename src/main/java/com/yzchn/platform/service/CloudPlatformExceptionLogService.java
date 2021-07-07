package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.CloudPlatformExceptionLogDao;
import com.yzchn.platform.model.dto.CloudPlatformExceptionLogDTO;
import com.yzchn.platform.model.entity.CloudPlatformExceptionLog;

import java.util.List;

/**
 * (CloudPlatformExceptionLog)表服务接口
 *
 * @author songyuan
 * @since 2021-06-25 10:09:53
 */
public interface CloudPlatformExceptionLogService extends IService<CloudPlatformExceptionLog> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-25 10:09:53
     */
    CloudPlatformExceptionLog selectCloudPlatformExceptionLogById(Long id);

    /**
     * @param cloudPlatformExceptionLogdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-25 10:09:53
     */
    IPage<CloudPlatformExceptionLog> findCloudPlatformExceptionLogSelectList(CloudPlatformExceptionLogDTO cloudPlatformExceptionLogdto);


    /**
     * @param cloudPlatformExceptionLogdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-25 10:09:53
     */
    void insert(CloudPlatformExceptionLogDTO cloudPlatformExceptionLogdto);

    /**
     * @param cloudPlatformExceptionLogdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-25 10:09:53
     */
    CloudPlatformExceptionLog update(CloudPlatformExceptionLogDTO cloudPlatformExceptionLogdto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-25 10:09:53
     */
    void deleteById(List<Long> idList);
}

