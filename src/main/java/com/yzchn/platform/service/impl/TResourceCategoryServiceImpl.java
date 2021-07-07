package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TResourceCategoryDao;
import com.yzchn.platform.model.dto.TResourceCategoryDTO;
import com.yzchn.platform.model.entity.TResourceCategory;
import com.yzchn.platform.model.vo.TResourceCategoryVO;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 资源分类表(TResourceCategory)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-23 11:07:27
 */
@Service("tResourceCategoryService")
public class TResourceCategoryServiceImpl extends ServiceImpl<TResourceCategoryDao, TResourceCategory> implements TResourceCategoryService {

    @Autowired
    private SecurityUserService securityUserService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:07:27
     */
    @Override
    public TResourceCategory selectTResourceCategoryById(Long id) {
        LambdaQueryWrapper<TResourceCategory> tResourceCategorylam = new LambdaQueryWrapper<TResourceCategory>();
        tResourceCategorylam.eq(TResourceCategory::getId, id);
        return baseMapper.selectOne(tResourceCategorylam);
    }

    /**
     * @param tResourceCategorydto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:07:27
     */
    @Override
    public IPage<TResourceCategory> findTResourceCategorySelectList(TResourceCategoryDTO tResourceCategorydto) {
        IPage<TResourceCategory> tResourceCategoryPage = new Page<>(tResourceCategorydto.getPageNum(), tResourceCategorydto.getPageSize());
        LambdaQueryWrapper<TResourceCategory> tResourceCategoryWrapper = new LambdaQueryWrapper<>();
        tResourceCategoryWrapper.eq(TResourceCategory::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode()).orderByDesc(TResourceCategory::getSort);
        return baseMapper.selectPage(tResourceCategoryPage, tResourceCategoryWrapper);
    }

    /**
     * @param tResourceCategorydto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:07:27
     */
    @Override
    public void insert(TResourceCategoryDTO tResourceCategorydto) {
        TResourceCategory tResourceCategory = ModelUtil.copyModel(tResourceCategorydto, TResourceCategory.class);
        tResourceCategory.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
        tResourceCategory.setCreatedBy(securityUserService.getUserName());
        tResourceCategory.setCreatedTime(LocalDateTime.now());
        baseMapper.insert(tResourceCategory);
    }


    /**
     * @param tResourceCategorydto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:07:27
     */
    @Override
    public TResourceCategory update(TResourceCategoryDTO tResourceCategorydto) {
        TResourceCategory tResourceCategory = ModelUtil.copyModel(tResourceCategorydto, TResourceCategory.class);
        tResourceCategory.setUpdatedBy(securityUserService.getUserName());
        tResourceCategory.setUpdatedTime(LocalDateTime.now());
        baseMapper.updateById(tResourceCategory);
        return baseMapper.selectById(tResourceCategory.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:07:27
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
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得资源分类下拉列表
     * @date 2021/6/23 16:33
     */
    @Override
    public Map<Long, String> categorySelect() {
        Map<Long, String> resultMap = new HashMap<>();
        LambdaQueryWrapper<TResourceCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TResourceCategory::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode())
                .orderByAsc(TResourceCategory::getSort);
        List<TResourceCategory> tResourceCategories = baseMapper.selectList(lambdaQueryWrapper);
        tResourceCategories.forEach(item -> {
            resultMap.put(item.getId(), item.getName());
        });
        return resultMap;
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO        获得分类信息
     * @date 2021/6/24 15:48
     */
    @Override
    public List<TResourceCategoryVO> findCategoryList() {
        LambdaQueryWrapper<TResourceCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TResourceCategory::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode())
                .orderByDesc(TResourceCategory::getSort);
        return ModelUtil.copyModelList(baseMapper.selectList(lambdaQueryWrapper), TResourceCategoryVO.class);
    }
}
