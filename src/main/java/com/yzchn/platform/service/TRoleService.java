package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.model.dto.TRoleDTO;
import com.yzchn.platform.model.entity.TRole;

import java.util.List;
import java.util.Map;

/**
 * 角色表(TRole)表服务接口
 *
 * @author songyuan
 * @since 2021-06-22 16:25:35
 */
public interface TRoleService extends IService<TRole> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-22 16:25:35
     */
    TRole selectTRoleById(Long id);

    /**
     * @param tRoledto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-22 16:25:35
     */
    IPage<TRole> findTRoleSelectList(TRoleDTO tRoledto);


    /**
     * @param tRoledto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-22 16:25:35
     */
    void insert(TRoleDTO tRoledto);

    /**
     * @param tRoledto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-22 16:25:35
     */
    TRole update(TRoleDTO tRoledto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-22 16:25:35
     */
    void deleteById(List<Long> idList);

    /**
     * @param userId
     * @return java.util.List<com.yzchn.platform.model.entity.TRole>
     * @author 洛无异
     * @description //TODO    获得当前登录用户角色的集合
     * @date 2021/6/23 14:34
     */
    List<TRole> getRoleList(Long userId);

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO  获得角色下拉列表
     * @date 2021/6/23 16:07
     */
    Map<Long, String> getRoleSelect();

}

