package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TRoleDao;
import com.yzchn.platform.model.dto.TRoleDTO;
import com.yzchn.platform.model.entity.TRole;
import com.yzchn.platform.model.entity.TUserRoleRelation;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TRoleService;
import com.yzchn.platform.service.TUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色表(TRole)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-22 16:25:35
 */
@Service("tRoleService")
public class TRoleServiceImpl extends ServiceImpl<TRoleDao, TRole> implements TRoleService {

    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private TUserRoleRelationService userRoleRelationService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-22 16:25:35
     */
    @Override
    public TRole selectTRoleById(Long id) {
        LambdaQueryWrapper<TRole> tRolelam = new LambdaQueryWrapper<TRole>();
        tRolelam.eq(TRole::getId, id);
        return baseMapper.selectOne(tRolelam);
    }

    /**
     * @param tRoledto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-22 16:25:35
     */
    @Override
    public IPage<TRole> findTRoleSelectList(TRoleDTO tRoledto) {
        IPage<TRole> tRolePage = new Page<>(tRoledto.getPageNum(), tRoledto.getPageSize());
        LambdaQueryWrapper<TRole> tRoleWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isObjectNotEmpty(tRoledto.getName())) {
            tRoleWrapper.eq(TRole::getName, tRoledto.getName());
        }
        tRoleWrapper.eq(TRole::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        tRoleWrapper.orderByDesc(TRole::getCreatedTime);
        return baseMapper.selectPage(tRolePage, tRoleWrapper);
    }

    /**
     * @param tRoledto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-22 16:25:35
     */
    @Override
    public void insert(TRoleDTO tRoledto) {
        TRole tRole = ModelUtil.copyModel(tRoledto, TRole.class);
        tRole.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
        tRole.setCreatedBy(securityUserService.getUserName());
        tRole.setCreatedTime(LocalDateTime.now());
        baseMapper.insert(tRole);
    }


    /**
     * @param tRoledto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-22 16:25:35
     */
    @Override
    public TRole update(TRoleDTO tRoledto) {
        TRole tRole = ModelUtil.copyModel(tRoledto, TRole.class);
        tRole.setUpdatedBy(securityUserService.getUserName());
        tRole.setUpdatedTime(LocalDateTime.now());
        baseMapper.updateById(tRole);
        return baseMapper.selectById(tRole.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-22 16:25:35
     */
    @Override
    public void deleteById(List<Long> idList) {
        if (idList != null && idList.size() > 0) {
            LambdaUpdateWrapper<TRole> roleLambdaQueryWrapper = new LambdaUpdateWrapper<>();
            roleLambdaQueryWrapper.in(TRole::getId, idList);
            roleLambdaQueryWrapper.set(TRole::getIsDelete, ModelIsDelEnum.INVALID.getCode()).set(TRole::getUpdatedBy,
                    securityUserService.getUserId()).set(TRole::getUpdatedTime, LocalDateTime.now());
            update(roleLambdaQueryWrapper);
        }
    }

    /**
     * @param userId
     * @return java.util.List<com.yzchn.platform.model.entity.TRole>
     * @author 洛无异
     * @description //TODO    获得当前登录用户角色的集合
     * @date 2021/6/23 14:34
     */
    @Override
    public List<TRole> getRoleList(Long userId) {
        LambdaQueryWrapper<TUserRoleRelation> userRoleRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleRelationLambdaQueryWrapper.select(TUserRoleRelation::getRoleId);
        userRoleRelationLambdaQueryWrapper.eq(TUserRoleRelation::getUserId, userId);
        List<TUserRoleRelation> tUserRoleRelations = userRoleRelationService.getBaseMapper().selectList(userRoleRelationLambdaQueryWrapper);
        List<Long> roleIds = tUserRoleRelations.stream().map(TUserRoleRelation::getRoleId).collect(Collectors.toList());
        LambdaQueryWrapper<TRole> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.eq(TRole::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode()).in(TRole::getId, roleIds);
        return baseMapper.selectList(roleLambdaQueryWrapper);
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO  获得角色下拉列表
     * @date 2021/6/23 16:07
     */
    @Override
    public Map<Long, String> getRoleSelect() {
        Map<Long, String> resultMap = new HashMap<>();
        LambdaQueryWrapper<TRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TRole::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode()).orderByAsc(TRole::getSort);
        List<TRole> tRoles = baseMapper.selectList(lambdaQueryWrapper);
        tRoles.forEach(item -> {
            resultMap.put(item.getId(), item.getName());
        });
        return resultMap;
    }
}
