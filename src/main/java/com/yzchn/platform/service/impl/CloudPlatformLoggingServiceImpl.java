package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.CloudPlatformLoggingDao;
import com.yzchn.platform.model.dto.CloudPlatformLoggingDTO;
import com.yzchn.platform.model.entity.CloudPlatformLogging;
import com.yzchn.platform.service.CloudPlatformLoggingService;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;
import java.time.LocalDateTime;

/**
 * (CloudPlatformLogging)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-25 10:09:53
 */
@Service("cloudPlatformLoggingService")
public class CloudPlatformLoggingServiceImpl extends ServiceImpl<CloudPlatformLoggingDao, CloudPlatformLogging> implements CloudPlatformLoggingService {

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public CloudPlatformLogging selectCloudPlatformLoggingById(Long id) {
        LambdaQueryWrapper<CloudPlatformLogging> cloudPlatformLogginglam = new LambdaQueryWrapper<CloudPlatformLogging>();
        cloudPlatformLogginglam.eq(CloudPlatformLogging::getId, id);
        return baseMapper.selectOne(cloudPlatformLogginglam);
    }

    /**
     * @param cloudPlatformLoggingdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public IPage<CloudPlatformLogging> findCloudPlatformLoggingSelectList(CloudPlatformLoggingDTO cloudPlatformLoggingdto) {
        IPage<CloudPlatformLogging> cloudPlatformLoggingPage = new Page<>(cloudPlatformLoggingdto.getPageNum(), cloudPlatformLoggingdto.getPageSize());
        LambdaQueryWrapper<CloudPlatformLogging> cloudPlatformLoggingWrapper = new LambdaQueryWrapper<>();

        return baseMapper.selectPage(cloudPlatformLoggingPage, cloudPlatformLoggingWrapper);
    }

    /**
     * @param cloudPlatformLoggingdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public void insert(CloudPlatformLoggingDTO cloudPlatformLoggingdto) {
        CloudPlatformLogging cloudPlatformLogging = ModelUtil.copyModel(cloudPlatformLoggingdto, CloudPlatformLogging.class);
        cloudPlatformLogging.setCreateTime(LocalDateTime.now());
        baseMapper.insert(cloudPlatformLogging);
    }


    /**
     * @param cloudPlatformLoggingdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-25 10:09:53
     */
    @Override
    public CloudPlatformLogging update(CloudPlatformLoggingDTO cloudPlatformLoggingdto) {
        CloudPlatformLogging cloudPlatformLogging = ModelUtil.copyModel(cloudPlatformLoggingdto, CloudPlatformLogging.class);
        baseMapper.updateById(cloudPlatformLogging);
        return baseMapper.selectById(cloudPlatformLogging.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public void deleteById(List<Long> idList) {
        if (idList != null && idList.size() > 0) {
            for (int i = 0; i < idList.size(); i++) {
                baseMapper.deleteById(idList.get(i));
            }
        }
    }
}
