package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TRoleMenuRelationDao;
import com.yzchn.platform.model.dto.TRoleMenuRelationDTO;
import com.yzchn.platform.model.entity.TRoleMenuRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色菜单关联表(TRoleMenuRelation)表服务接口
 *
 * @author songyuan
 * @since 2021-06-23 11:07:27
 */
public interface TRoleMenuRelationService extends IService<TRoleMenuRelation> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:27
     */
    TRoleMenuRelation selectTRoleMenuRelationById(Long id);

    /**
     * @param tRoleMenuRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:27
     */
    IPage<TRoleMenuRelation> findTRoleMenuRelationSelectList(TRoleMenuRelationDTO tRoleMenuRelationdto);


    /**
     * @param tRoleMenuRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:27
     */
    void insert(TRoleMenuRelationDTO tRoleMenuRelationdto);

    /**
     * @param tRoleMenuRelationdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:27
     */
    TRoleMenuRelation update(TRoleMenuRelationDTO tRoleMenuRelationdto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:27
     */
    void deleteById(List<Long> idList);

    /**
     * @param tRoleMenuRelation
     * @return void
     * @author 洛无异
     * @description //TODO     给角色分配菜单
     * @date 2021/6/23 15:15
     */
    void allocMenu(TRoleMenuRelationDTO tRoleMenuRelation);

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO   获得所有选中菜单的id
     * @date 2021/6/23 15:33
     */
    List<Long> getMenuIds(Long roleId);
}

