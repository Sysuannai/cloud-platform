package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TUserRoleRelationDao;
import com.yzchn.platform.model.dto.TUserRoleRelationDTO;
import com.yzchn.platform.model.entity.TRole;
import com.yzchn.platform.model.entity.TUserRoleRelation;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 用户角色关联表(TUserRoleRelation)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-23 11:07:30
 */
@Service("tUserRoleRelationService")
public class TUserRoleRelationServiceImpl extends ServiceImpl<TUserRoleRelationDao, TUserRoleRelation> implements TUserRoleRelationService {

    @Autowired
    private SecurityUserService securityUserService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:30
     */
    @Override
    public TUserRoleRelation selectTUserRoleRelationById(Long id) {
        LambdaQueryWrapper<TUserRoleRelation> tUserRoleRelationlam = new LambdaQueryWrapper<TUserRoleRelation>();
        tUserRoleRelationlam.eq(TUserRoleRelation::getId, id);
        return baseMapper.selectOne(tUserRoleRelationlam);
    }

    /**
     * @param tUserRoleRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:30
     */
    @Override
    public IPage<TUserRoleRelation> findTUserRoleRelationSelectList(TUserRoleRelationDTO tUserRoleRelationdto) {
        IPage<TUserRoleRelation> tUserRoleRelationPage = new Page<>(tUserRoleRelationdto.getPageNum(), tUserRoleRelationdto.getPageSize());
        LambdaQueryWrapper<TUserRoleRelation> tUserRoleRelationWrapper = new LambdaQueryWrapper<>();

        return baseMapper.selectPage(tUserRoleRelationPage, tUserRoleRelationWrapper);
    }

    /**
     * @param tUserRoleRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:30
     */
    @Override
    public void insert(TUserRoleRelationDTO tUserRoleRelationdto) {
        TUserRoleRelation tUserRoleRelation = ModelUtil.copyModel(tUserRoleRelationdto, TUserRoleRelation.class);
        baseMapper.insert(tUserRoleRelation);
    }


    /**
     * @param tUserRoleRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:30
     */
    @Override
    public TUserRoleRelation update(TUserRoleRelationDTO tUserRoleRelationdto) {
        TUserRoleRelation tUserRoleRelation = ModelUtil.copyModel(tUserRoleRelationdto, TUserRoleRelation.class);
        baseMapper.updateById(tUserRoleRelation);
        return baseMapper.selectById(tUserRoleRelation.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:30
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
     * @param tUserRoleRelation
     * @return void
     * @author 洛无异
     * @description //TODO     给用户分配角色
     * @date 2021/6/23 14:49
     */
    @Override
    public void assigningRoles(TUserRoleRelationDTO tUserRoleRelation) {
        List<TUserRoleRelation> tUserRoleRelations = new ArrayList<>();
        tUserRoleRelation.getRoleIds().forEach(item -> {
            TUserRoleRelation userRoleRelation = new TUserRoleRelation();
            userRoleRelation.setRoleId(item);
            userRoleRelation.setUserId(tUserRoleRelation.getUserId());
            tUserRoleRelations.add(userRoleRelation);
        });
        saveBatch(tUserRoleRelations, 5);
    }

}
