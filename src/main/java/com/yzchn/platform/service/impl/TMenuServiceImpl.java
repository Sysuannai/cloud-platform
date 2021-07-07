package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TMenuDao;
import com.yzchn.platform.model.bo.MenuListBO;
import com.yzchn.platform.model.dto.TMenuDTO;
import com.yzchn.platform.model.entity.TMenu;
import com.yzchn.platform.model.entity.TRoleMenuRelation;
import com.yzchn.platform.model.vo.TMenuNodeVO;
import com.yzchn.platform.model.vo.TMenuVO;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TMenuService;
import com.yzchn.platform.service.TRoleMenuRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单表(TMenu)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-23 11:04:30
 */
@Service("tMenuService")
public class TMenuServiceImpl extends ServiceImpl<TMenuDao, TMenu> implements TMenuService {

    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private TRoleMenuRelationService menuRelationService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-23 11:04:30
     */
    @Override
    public TMenu selectTMenuById(Long id) {
        LambdaQueryWrapper<TMenu> tMenulam = new LambdaQueryWrapper<TMenu>();
        tMenulam.eq(TMenu::getId, id);
        return baseMapper.selectOne(tMenulam);
    }

    /**
     * @param tMenudto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-23 11:04:30
     */
    @Override
    public IPage findTMenuSelectList(TMenuDTO tMenudto) {
        IPage<TMenu> tMenuPage = new Page<>(tMenudto.getPageNum(), tMenudto.getPageSize());
        LambdaQueryWrapper<TMenu> tMenuWrapper = new LambdaQueryWrapper<>();
        tMenuWrapper.eq(TMenu::getParentId, ModelIsDelEnum.EFFECTIVE.getCode())
                .eq(TMenu::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode()).orderByAsc(TMenu::getSort);
        return baseMapper.selectPage(tMenuPage, tMenuWrapper);
    }

    /**
     * @param tMenudto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:04:30
     */
    @Override
    public void insert(TMenuDTO tMenudto) {
        TMenu tMenu = ModelUtil.copyModel(tMenudto, TMenu.class);
        tMenu.setCreatedBy(securityUserService.getUserName());
        tMenu.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
        tMenu.setCreatedTime(LocalDateTime.now());
        baseMapper.insert(tMenu);
    }


    /**
     * @param tMenudto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-23 11:04:30
     */
    @Override
    public TMenu update(TMenuDTO tMenudto) {
        TMenu tMenu = ModelUtil.copyModel(tMenudto, TMenu.class);
        tMenu.setUpdatedBy(securityUserService.getUserName());
        tMenu.setUpdatedTime(LocalDateTime.now());
        baseMapper.updateById(tMenu);
        return baseMapper.selectById(tMenu.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-23 11:04:30
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(List<Long> idList) {
        idList.forEach(id -> {
            // 查询主表查看是否有二级菜单,如果有不能删除
            LambdaQueryWrapper<TMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TMenu::getParentId, id).eq(TMenu::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
            Integer integer = baseMapper.selectCount(lambdaQueryWrapper);
            if (integer >= 1) {
                throw new BusinessException(BusinessEnum.MENU_DELETE);
            }
            // 进行删除操作
            LambdaUpdateWrapper<TMenu> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(TMenu::getId, id)
                    .set(TMenu::getUpdatedBy, securityUserService.getUserId())
                    .set(TMenu::getUpdatedTime, LocalDateTime.now())
                    .set(TMenu::getIsDelete, ModelIsDelEnum.INVALID.getCode());
            update(lambdaUpdateWrapper);
            // 清空中间表数据
            LambdaQueryWrapper<TRoleMenuRelation> menuRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            menuRelationLambdaQueryWrapper.eq(TRoleMenuRelation::getMenuId, id);
            menuRelationService.getBaseMapper().delete(menuRelationLambdaQueryWrapper);
        });
    }

    /**
     * @param tMenudto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有二级数据
     * @date 2021-06-23 11:04:44
     */
    @Override
    public IPage findSecondaryMenuSelectList(TMenuDTO tMenudto) {
        IPage<TMenu> tMenuPage = new Page<>(tMenudto.getPageNum(), tMenudto.getPageSize());
        LambdaQueryWrapper<TMenu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(TMenu::getParentId, tMenudto.getParentId())
                .eq(TMenu::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode()).orderByAsc(TMenu::getSort);
        return baseMapper.selectPage(tMenuPage, menuLambdaQueryWrapper);
    }

    /**
     * @param userId
     * @return java.util.List<com.yzchn.platform.model.bo.MenuListBO>
     * @author 洛无异
     * @description //TODO    获得用户菜单信息
     * @date 2021/6/23 14:15
     */
    @Override
    public List<MenuListBO> getMenuList(Long userId) {
        // 获得当前角色所有的菜单信息
        QueryWrapper<TMenu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("arr.user_id", userId);
        menuQueryWrapper.isNotNull("m.id");
        menuQueryWrapper.groupBy("m.id");
        return baseMapper.getMenuList(menuQueryWrapper);
    }

    /**
     * @return java.util.List<com.yzchn.platform.model.vo.TMenuNodeVO>
     * @author 洛无异
     * @description //TODO 分配菜单树形状菜单查询
     * @date 2021/6/23 15:01
     */
    @Override
    public List<TMenuNodeVO> getMenuNodeList() {
        LambdaQueryWrapper<TMenu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(TMenu::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        List<TMenu> tMenus = baseMapper.selectList(menuLambdaQueryWrapper);
        List<TMenuNodeVO> result = tMenus.stream().filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> (covertMenuNode(menu, tMenus)))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * @param pid 父Id
     * @return java.util.Map<java.lang.Long, java.lang.String>
     * @author 洛无异
     * @description //TODO   获得父级别菜单
     * @date 2021/6/23 15:57
     */
    @Override
    public Map<Long, String> getMenuSelect(Long pid) {
        Map<Long, String> resultMap = new HashMap<>();
        LambdaQueryWrapper<TMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TMenu::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        if (StringUtils.isObjectEmpty(pid)) {
            resultMap.put(0L, "无上级菜单");
            lambdaQueryWrapper.eq(TMenu::getParentId, ModelIsDelEnum.EFFECTIVE.getCode());
        }
        List<TMenu> tMenus = baseMapper.selectList(lambdaQueryWrapper);
        tMenus.forEach(menu -> {
            resultMap.put(menu.getId(), menu.getTitle());
        });
        return resultMap;
    }


    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private TMenuNodeVO covertMenuNode(TMenu menu, List<TMenu> menuList) {
        TMenuNodeVO node = ModelUtil.copyModel(menu, TMenuNodeVO.class);
        List<TMenuNodeVO> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
