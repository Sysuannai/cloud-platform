package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TResourceDao;
import com.yzchn.platform.model.dto.TResourceDTO;
import com.yzchn.platform.model.entity.TResource;
import com.yzchn.platform.model.vo.TResourceVO;

import java.util.List;

/**
 * 系统资源表(TResource)表服务接口
 *
 * @author songyuan
 * @since 2021-06-21 16:20:40
 */
public interface TResourceService extends IService<TResource> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-21 16:20:40
     */
    TResource selectTResourceById(Long id);

    /**
     * @param tResourcedto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-21 16:20:40
     */
    IPage<TResource> findTResourceSelectList(TResourceDTO tResourcedto);


    /**
     * @param tResourcedto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-21 16:20:40
     */
    void insert(TResourceDTO tResourcedto);

    /**
     * @param tResourcedto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-21 16:20:40
     */
    TResource update(TResourceDTO tResourcedto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-21 16:20:40
     */
    void deleteById(List<Long> idList);

    /**
     * @param id
     * @return java.util.List<com.yzchn.platform.model.entity.TResource>
     * @author 洛无异
     * @description //TODO  获得资源路径
     * @date 2021/6/21 16:47
     */
    List<TResource> getResourceList(Long id);

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得所有的资源信息
     * @date 2021/6/24 15:56
     */
    List<TResourceVO> findResource();
    /**
     * @param roleId 角色Id
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO   获得选中的资源信息
     * @date 2021/6/24 16:02
     */
    List<TResourceVO> findSelectedResource(Long roleId);
}

