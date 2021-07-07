package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TMenuDao;
import com.yzchn.platform.model.bo.MenuListBO;
import com.yzchn.platform.model.dto.TMenuDTO;
import com.yzchn.platform.model.entity.TMenu;
import com.yzchn.platform.model.vo.TMenuNodeVO;
import com.yzchn.platform.model.vo.TMenuVO;

import java.util.List;
import java.util.Map;

/**
 * 菜单表(TMenu)表服务接口
 *
 * @author songyuan
 * @since 2021-06-23 11:04:30
 */
public interface TMenuService extends IService<TMenu> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:04:30
     */
    TMenu selectTMenuById(Long id);

    /**
     * @param tMenudto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:04:30
     */
    IPage<TMenuVO> findTMenuSelectList(TMenuDTO tMenudto);


    /**
     * @param tMenudto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:04:30
     */
    void insert(TMenuDTO tMenudto);

    /**
     * @param tMenudto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:04:30
     */
    TMenu update(TMenuDTO tMenudto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:04:30
     */
    void deleteById(List<Long> idList);

    /**
     * @param tMenudto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有二级数据
     * @date 2021-06-23 11:04:44
     */
    IPage<TMenu> findSecondaryMenuSelectList(TMenuDTO tMenudto);

    /**
     * @param userId
     * @return java.util.List<com.yzchn.platform.model.bo.MenuListBO>
     * @author 洛无异
     * @description //TODO    获得用户菜单信息
     * @date 2021/6/23 14:15
     */
    List<MenuListBO> getMenuList(Long userId);

    /**
     * @return java.util.List<com.yzchn.platform.model.vo.TMenuNodeVO>
     * @author 洛无异
     * @description //TODO 分配菜单树形状菜单查询
     * @date 2021/6/23 15:01
     */
    List<TMenuNodeVO> getMenuNodeList();

    /**
     * @param pid 父Id
     * @return java.util.Map<java.lang.Long, java.lang.String>
     * @author 洛无异
     * @description //TODO   获得父级别菜单
     * @date 2021/6/23 15:57
     */
    Map<Long, String> getMenuSelect(Long pid);
}

