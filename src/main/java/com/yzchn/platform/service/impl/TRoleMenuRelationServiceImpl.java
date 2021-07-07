package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TRoleMenuRelationDao;
import com.yzchn.platform.model.dto.TRoleMenuRelationDTO;
import com.yzchn.platform.model.entity.TRoleMenuRelation;
import com.yzchn.platform.service.TRoleMenuRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * 角色菜单关联表(TRoleMenuRelation)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-23 11:07:28
 */
@Service("tRoleMenuRelationService")
public class TRoleMenuRelationServiceImpl extends ServiceImpl<TRoleMenuRelationDao, TRoleMenuRelation> implements TRoleMenuRelationService {

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public TRoleMenuRelation selectTRoleMenuRelationById(Long id) {
        LambdaQueryWrapper<TRoleMenuRelation> tRoleMenuRelationlam = new LambdaQueryWrapper<TRoleMenuRelation>();
        tRoleMenuRelationlam.eq(TRoleMenuRelation::getId, id);
        return baseMapper.selectOne(tRoleMenuRelationlam);
    }

    /**
     * @param tRoleMenuRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public IPage<TRoleMenuRelation> findTRoleMenuRelationSelectList(TRoleMenuRelationDTO tRoleMenuRelationdto) {
        IPage<TRoleMenuRelation> tRoleMenuRelationPage = new Page<>(tRoleMenuRelationdto.getPageNum(), tRoleMenuRelationdto.getPageSize());
        LambdaQueryWrapper<TRoleMenuRelation> tRoleMenuRelationWrapper = new LambdaQueryWrapper<>();

        return baseMapper.selectPage(tRoleMenuRelationPage, tRoleMenuRelationWrapper);
    }

    /**
     * @param tRoleMenuRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:28
     */
    @Override
    public void insert(TRoleMenuRelationDTO tRoleMenuRelationdto) {
        TRoleMenuRelation tRoleMenuRelation = ModelUtil.copyModel(tRoleMenuRelationdto, TRoleMenuRelation.class);
        baseMapper.insert(tRoleMenuRelation);
    }


    /**
     * @param tRoleMenuRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:28
     */
    @Override
    public TRoleMenuRelation update(TRoleMenuRelationDTO tRoleMenuRelationdto) {
        TRoleMenuRelation tRoleMenuRelation = ModelUtil.copyModel(tRoleMenuRelationdto, TRoleMenuRelation.class);
        baseMapper.updateById(tRoleMenuRelation);
        return baseMapper.selectById(tRoleMenuRelation.getId());
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
     * @param tRoleMenuRelation
     * @return void
     * @author 洛无异
     * @description //TODO     给角色分配菜单
     * @date 2021/6/23 15:15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void allocMenu(TRoleMenuRelationDTO tRoleMenuRelation) {
        // 先删除角色所有的菜单
        LambdaQueryWrapper<TRoleMenuRelation> roleMenuRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleMenuRelationLambdaQueryWrapper.eq(TRoleMenuRelation::getRoleId, tRoleMenuRelation.getRoleId());
        baseMapper.delete(roleMenuRelationLambdaQueryWrapper);
        List<TRoleMenuRelation> roleMenuRelations = new ArrayList<>();
        tRoleMenuRelation.getMenuIds().forEach(item -> {
            TRoleMenuRelation roleMenuRelation = new TRoleMenuRelation();
            roleMenuRelation.setMenuId(item);
            roleMenuRelation.setRoleId(tRoleMenuRelation.getRoleId());
            roleMenuRelations.add(roleMenuRelation);
        });
        saveBatch(roleMenuRelations, 10);
    }

    /**
     * @param roleId
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO   获得所有选中菜单的id
     * @date 2021/6/23 15:33
     */
    @Override
    public List<Long> getMenuIds(Long roleId) {
        LambdaQueryWrapper<TRoleMenuRelation> roleMenuRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleMenuRelationLambdaQueryWrapper.eq(TRoleMenuRelation::getRoleId, roleId);
        List<TRoleMenuRelation> roleMenuRelations = baseMapper.selectList(roleMenuRelationLambdaQueryWrapper);
        return roleMenuRelations.stream().map(TRoleMenuRelation::getMenuId).collect(Collectors.toList());
    }
}
