package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TRoleResourceRelationDao;
import com.yzchn.platform.model.dto.TRoleResourceRelationDTO;
import com.yzchn.platform.model.entity.TRoleResourceRelation;

import java.util.List;

/**
 * 角色资源关联表(TRoleResourceRelation)表服务接口
 *
 * @author songyuan
 * @since 2021-06-23 11:07:28
 */
public interface TRoleResourceRelationService extends IService<TRoleResourceRelation> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:28
     */
    TRoleResourceRelation selectTRoleResourceRelationById(Long id);

    /**
     * @param tRoleResourceRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:28
     */
    IPage<TRoleResourceRelation> findTRoleResourceRelationSelectList(TRoleResourceRelationDTO tRoleResourceRelationdto);


    /**
     * @param tRoleResourceRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:28
     */
    void insert(TRoleResourceRelationDTO tRoleResourceRelationdto);

    /**
     * @param tRoleResourceRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:28
     */
    TRoleResourceRelation update(TRoleResourceRelationDTO tRoleResourceRelationdto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:28
     */
    void deleteById(List<Long> idList);

    /**
     * @param tRoleResourceRelation
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 给角色分配资源信息
     * @date 2021/6/24 13:33
     */
    void allocResource(TRoleResourceRelationDTO tRoleResourceRelation);

    /**
     * @param roleId
     * @return java.util.List<java.lang.Long>
     * @author 洛无异
     * @description //TODO      获得当前角色所有资源id
     * @date 2021/6/24 16:07
     */
    List<Long> findResourceIds(Long roleId);
}

