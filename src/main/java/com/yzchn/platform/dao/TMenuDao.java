package com.yzchn.platform.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yzchn.platform.model.bo.MenuListBO;
import com.yzchn.platform.model.entity.TMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单表(TMenu)表数据库访问层
 *
 * @author songyuan
 * @since 2021-06-23 11:04:30
 */
@Mapper
public interface TMenuDao extends BaseMapper<TMenu> {
    @Select("SELECT\n" +
            "\tm.id id,\n" +
            "\tm.parent_id parentId,\n" +
            "\tm.created_time createTime,\n" +
            "\tm.title title,\n" +
            "\tm.sort sort,\n" +
            "\tm.NAME NAME,\n" +
            "\tm.icon icon\n" +
            "FROM\n" +
            "\tt_user_role_relation arr\n" +
            "\tLEFT JOIN t_role r ON arr.role_id = r.id\n" +
            "\tLEFT JOIN t_role_menu_relation rmr ON r.id = rmr.role_id\n" +
            "\tLEFT JOIN t_menu m ON rmr.menu_id = m.id  ${ew.customSqlSegment} ")
    List<MenuListBO> getMenuList(@Param(Constants.WRAPPER) QueryWrapper<TMenu> menuQueryWrapper);

}
