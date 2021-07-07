package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TUserRoleRelationDao;
import com.yzchn.platform.model.dto.TUserRoleRelationDTO;
import com.yzchn.platform.model.entity.TUserRoleRelation;

import java.util.List;
import java.util.Map;

/**
 * 用户角色关联表(TUserRoleRelation)表服务接口
 *
 * @author songyuan
 * @since 2021-06-23 11:07:30
 */
public interface TUserRoleRelationService extends IService<TUserRoleRelation> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:30
     */
    TUserRoleRelation selectTUserRoleRelationById(Long id);

    /**
     * @param tUserRoleRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:30
     */
    IPage<TUserRoleRelation> findTUserRoleRelationSelectList(TUserRoleRelationDTO tUserRoleRelationdto);


    /**
     * @param tUserRoleRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:30
     */
    void insert(TUserRoleRelationDTO tUserRoleRelationdto);

    /**
     * @param tUserRoleRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:30
     */
    TUserRoleRelation update(TUserRoleRelationDTO tUserRoleRelationdto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:30
     */
    void deleteById(List<Long> idList);

    /**
     * @param tUserRoleRelation
     * @return void
     * @author 洛无异
     * @description //TODO     给用户分配角色
     * @date 2021/6/23 14:49
     */
    void assigningRoles(TUserRoleRelationDTO tUserRoleRelation);


}

