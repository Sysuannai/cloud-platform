package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.Constants;
import com.yzchn.platform.common.security.component.DynamicSecurityMetadataSource;
import com.yzchn.platform.common.security.component.DynamicSecurityService;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TResourceDao;
import com.yzchn.platform.model.dto.TResourceDTO;
import com.yzchn.platform.model.entity.TResource;
import com.yzchn.platform.model.vo.TResourceVO;
import com.yzchn.platform.service.RedisService;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TResourceService;
import com.yzchn.platform.service.TRoleResourceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * 系统资源表(TResource)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-21 16:20:40
 */
@Service("tResourceService")
public class TResourceServiceImpl extends ServiceImpl<TResourceDao, TResource> implements TResourceService {

    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TRoleResourceRelationService roleResourceRelationService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-21 16:20:40
     */
    @Override
    public TResource selectTResourceById(Long id) {
        LambdaQueryWrapper<TResource> tResourcelam = new LambdaQueryWrapper<TResource>();
        tResourcelam.eq(TResource::getId, id);
        return baseMapper.selectOne(tResourcelam);
    }

    /**
     * @param tResourcedto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-21 16:20:40
     */
    @Override
    public IPage<TResource> findTResourceSelectList(TResourceDTO tResourcedto) {
        IPage<TResource> tResourcePage = new Page<>(tResourcedto.getPageNum(), tResourcedto.getPageSize());
        LambdaQueryWrapper<TResource> tResourceWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isObjectNotEmpty(tResourcedto.getName())) {
            tResourceWrapper.like(TResource::getName, tResourcedto.getName());
        }
        if (StringUtils.isObjectNotEmpty(tResourcedto.getUrl())) {
            tResourceWrapper.like(TResource::getUrl, tResourcedto.getUrl());
        }
        if (StringUtils.isObjectNotEmpty(tResourcedto.getCategoryId())) {
            tResourceWrapper.eq(TResource::getCategoryId, tResourcedto.getCategoryId());
        }
        tResourceWrapper.eq(TResource::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode())
                .orderByDesc(TResource::getCreatedTime);
        return baseMapper.selectPage(tResourcePage, tResourceWrapper);
    }

    /**
     * @param tResourcedto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-21 16:20:40
     */
    @Override
    public void insert(TResourceDTO tResourcedto) {
        TResource tResource = ModelUtil.copyModel(tResourcedto, TResource.class);
        tResource.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
        tResource.setCreatedBy(securityUserService.getUserName());
        tResource.setCreatedTime(LocalDateTime.now());
        baseMapper.insert(tResource);
        Integer integer = baseMapper.selectCount(new LambdaQueryWrapper<TResource>().eq(TResource::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode()));
        redisService.set(Constants.CLOUD_RESOURCE_SIZE, integer);
    }


    /**
     * @param tResourcedto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-21 16:20:40
     */
    @Override
    public TResource update(TResourceDTO tResourcedto) {
        TResource tResource = ModelUtil.copyModel(tResourcedto, TResource.class);
        tResource.setUpdatedBy(securityUserService.getUserName());
        tResource.setUpdatedTime(LocalDateTime.now());
        baseMapper.updateById(tResource);
        redisService.set(Constants.CLOUD_RESOURCE_SIZE, 0);
        return baseMapper.selectById(tResource.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-21 16:20:40
     */
    @Override
    public void deleteById(List<Long> idList) {
        LambdaUpdateWrapper<TResource> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(TResource::getIsDelete, ModelIsDelEnum.INVALID.getCode())
                .set(TResource::getUpdatedBy, securityUserService.getUserName()).set(TResource::getUpdatedTime, LocalDateTime.now());
        update(lambdaUpdateWrapper);
    }

    /**
     * @param id
     * @return java.util.List<com.yzchn.platform.model.entity.TResource>
     * @author 洛无异
     * @description //TODO  获得资源路径
     * @date 2021/6/21 16:47
     */
    @Override
    public List<TResource> getResourceList(Long id) {
        QueryWrapper<TResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ar.user_id", id).isNotNull("ur.id").groupBy("ur.id");
        return baseMapper.getResourceList(queryWrapper);
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得所有的资源信息
     * @date 2021/6/24 15:56
     */
    @Override
    public List<TResourceVO> findResource() {
        LambdaQueryWrapper<TResource> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(TResource::getId, TResource::getName, TResource::getCategoryId)
                .eq(TResource::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        return ModelUtil.copyModelList(baseMapper.selectList(lambdaQueryWrapper), TResourceVO.class);
    }

    /**
     * @param roleId 角色Id
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO   获得选中的资源信息
     * @date 2021/6/24 16:02
     */
    @Override
    public List<TResourceVO> findSelectedResource(Long roleId) {
        List<Long> resourceIds = roleResourceRelationService.findResourceIds(roleId);
        if (resourceIds == null || resourceIds.size() <= 0) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<TResource> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TResource::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode())
                .in(TResource::getId, resourceIds);
        return ModelUtil.copyModelList(baseMapper.selectList(lambdaQueryWrapper), TResourceVO.class);
    }
}
