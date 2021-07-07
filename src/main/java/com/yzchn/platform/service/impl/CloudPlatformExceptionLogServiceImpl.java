package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.CloudPlatformExceptionLogDao;
import com.yzchn.platform.model.dto.CloudPlatformExceptionLogDTO;
import com.yzchn.platform.model.entity.CloudPlatformExceptionLog;
import com.yzchn.platform.service.CloudPlatformExceptionLogService;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;
import java.time.LocalDateTime;

/**
 * (CloudPlatformExceptionLog)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-25 10:09:53
 */
@Service("cloudPlatformExceptionLogService")
public class CloudPlatformExceptionLogServiceImpl extends ServiceImpl<CloudPlatformExceptionLogDao, CloudPlatformExceptionLog> implements CloudPlatformExceptionLogService {

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public CloudPlatformExceptionLog selectCloudPlatformExceptionLogById(Long id) {
        LambdaQueryWrapper<CloudPlatformExceptionLog> cloudPlatformExceptionLoglam = new LambdaQueryWrapper<CloudPlatformExceptionLog>();
        cloudPlatformExceptionLoglam.eq(CloudPlatformExceptionLog::getId, id);
        return baseMapper.selectOne(cloudPlatformExceptionLoglam);
    }

    /**
     * @param cloudPlatformExceptionLogdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public IPage<CloudPlatformExceptionLog> findCloudPlatformExceptionLogSelectList(CloudPlatformExceptionLogDTO cloudPlatformExceptionLogdto) {
        IPage<CloudPlatformExceptionLog> cloudPlatformExceptionLogPage = new Page<>(cloudPlatformExceptionLogdto.getPageNum(), cloudPlatformExceptionLogdto.getPageSize());
        LambdaQueryWrapper<CloudPlatformExceptionLog> cloudPlatformExceptionLogWrapper = new LambdaQueryWrapper<>();

        return baseMapper.selectPage(cloudPlatformExceptionLogPage, cloudPlatformExceptionLogWrapper);
    }

    /**
     * @param cloudPlatformExceptionLogdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-25 10:09:53
     */
    @Override
    public void insert(CloudPlatformExceptionLogDTO cloudPlatformExceptionLogdto) {
        CloudPlatformExceptionLog cloudPlatformExceptionLog = ModelUtil.copyModel(cloudPlatformExceptionLogdto, CloudPlatformExceptionLog.class);
        cloudPlatformExceptionLog.setCreateTime(LocalDateTime.now());
        baseMapper.insert(cloudPlatformExceptionLog);
    }


    /**
     * @param cloudPlatformExceptionLogdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-25 10:09:53
     */
    @Override
    public CloudPlatformExceptionLog update(CloudPlatformExceptionLogDTO cloudPlatformExceptionLogdto) {
        CloudPlatformExceptionLog cloudPlatformExceptionLog = ModelUtil.copyModel(cloudPlatformExceptionLogdto, CloudPlatformExceptionLog.class);
        baseMapper.updateById(cloudPlatformExceptionLog);
        return baseMapper.selectById(cloudPlatformExceptionLog.getId());
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
