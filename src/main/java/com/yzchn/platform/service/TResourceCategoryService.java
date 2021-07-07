package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TResourceCategoryDao;
import com.yzchn.platform.model.dto.TResourceCategoryDTO;
import com.yzchn.platform.model.entity.TResourceCategory;
import com.yzchn.platform.model.vo.TResourceCategoryVO;

import java.util.List;
import java.util.Map;

/**
 * 资源分类表(TResourceCategory)表服务接口
 *
 * @author songyuan
 * @since 2021-06-23 11:07:27
 */
public interface TResourceCategoryService extends IService<TResourceCategory> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:27
     */
    TResourceCategory selectTResourceCategoryById(Long id);

    /**
     * @param tResourceCategorydto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:27
     */
    IPage<TResourceCategory> findTResourceCategorySelectList(TResourceCategoryDTO tResourceCategorydto);


    /**
     * @param tResourceCategorydto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:27
     */
    void insert(TResourceCategoryDTO tResourceCategorydto);

    /**
     * @param tResourceCategorydto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:27
     */
    TResourceCategory update(TResourceCategoryDTO tResourceCategorydto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:27
     */
    void deleteById(List<Long> idList);

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得资源分类下拉列表
     * @date 2021/6/23 16:33
     */
    Map<Long, String> categorySelect();


    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO        获得分类信息
     * @date 2021/6/24 15:48
     */
    List<TResourceCategoryVO> findCategoryList();

}

