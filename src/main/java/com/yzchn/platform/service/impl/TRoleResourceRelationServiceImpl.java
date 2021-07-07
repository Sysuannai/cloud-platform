package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TRoleResourceRelationDao;
import com.yzchn.platform.model.dto.TRoleResourceRelationDTO;
import com.yzchn.platform.model.entity.TRoleResourceRelation;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TRoleResourceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * 角色资源关联表(TRoleResourceRelation)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-23 11:07:28
 */
@Service("tRoleResourceRelationService")
public class TRoleResourceRelationServiceImpl extends ServiceImpl<TRoleResourceRelationDao, TRoleResourceRelation> implements TRoleResourceRelationService {

    @Autowired
    private SecurityUserService securityUserService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public TRoleResourceRelation selectTRoleResourceRelationById(Long id) {
        LambdaQueryWrapper<TRoleResourceRelation> tRoleResourceRelationlam = new LambdaQueryWrapper<TRoleResourceRelation>();
        tRoleResourceRelationlam.eq(TRoleResourceRelation::getId, id);
        return baseMapper.selectOne(tRoleResourceRelationlam);
    }

    /**
     * @param tRoleResourceRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public IPage<TRoleResourceRelation> findTRoleResourceRelationSelectList(TRoleResourceRelationDTO tRoleResourceRelationdto) {
        IPage<TRoleResourceRelation> tRoleResourceRelationPage = new Page<>(tRoleResourceRelationdto.getPageNum(), tRoleResourceRelationdto.getPageSize());
        LambdaQueryWrapper<TRoleResourceRelation> tRoleResourceRelationWrapper = new LambdaQueryWrapper<>();

        return baseMapper.selectPage(tRoleResourceRelationPage, tRoleResourceRelationWrapper);
    }

    /**
     * @param tRoleResourceRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public void insert(TRoleResourceRelationDTO tRoleResourceRelationdto) {
        TRoleResourceRelation tRoleResourceRelation = ModelUtil.copyModel(tRoleResourceRelationdto, TRoleResourceRelation.class);
        baseMapper.insert(tRoleResourceRelation);
    }


    /**
     * @param tRoleResourceRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:28
     */
    @Override
    public TRoleResourceRelation update(TRoleResourceRelationDTO tRoleResourceRelationdto) {
        TRoleResourceRelation tRoleResourceRelation = ModelUtil.copyModel(tRoleResourceRelationdto, TRoleResourceRelation.class);
        tRoleResourceRelation.setUpdatedTime(LocalDateTime.now());
        baseMapper.updateById(tRoleResourceRelation);
        return baseMapper.selectById(tRoleResourceRelation.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public void deleteById(List<Long> idList) {
        if (idList != null && idList.size() > 0) {
            for (int i = 0; i < idList.size(); i++) {
                baseMapper.deleteById(idList.get(i));
            }
        }
    }

    /**
     * @param tRoleResourceRelation
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 给角色分配资源信息
     * @date 2021/6/24 13:33
     */
    @Override
    public void allocResource(TRoleResourceRelationDTO tRoleResourceRelation) {
        List<TRoleResourceRelation> tRoleResourceRelations = new ArrayList<>();
        tRoleResourceRelation.getResourceIds().forEach(item -> {
            TRoleResourceRelation tRoleResourceRelation1 = new TRoleResourceRelation();
            tRoleResourceRelation1.setRoleId(tRoleResourceRelation.getRoleId());
            tRoleResourceRelation1.setResourceId(item);
            tRoleResourceRelation1.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
            tRoleResourceRelation1.setCreatedBy(securityUserService.getUserName());
            tRoleResourceRelation1.setCreatedTime(LocalDateTime.now());
            tRoleResourceRelations.add(tRoleResourceRelation1);
        });
        saveBatch(tRoleResourceRelations, 5);
    }

    /**
     * @param roleId
     * @return java.util.List<java.lang.Long>
     * @author 洛无异
     * @description //TODO      获得当前角色所有资源id
     * @date 2021/6/24 16:07
     */
    @Override
    public List<Long> findResourceIds(Long roleId) {
        LambdaQueryWrapper<TRoleResourceRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(TRoleResourceRelation::getResourceId)
                .eq(TRoleResourceRelation::getRoleId, roleId)
                .eq(TRoleResourceRelation::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        List<TRoleResourceRelation> resourceRelations = baseMapper.selectList(lambdaQueryWrapper);
        return resourceRelations.stream().map(TRoleResourceRelation::getResourceId).collect(Collectors.toList());
    }
}
