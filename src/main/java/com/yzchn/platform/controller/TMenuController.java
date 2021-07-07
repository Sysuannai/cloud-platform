package com.yzchn.platform.controller;


import com.yzchn.platform.common.OprLogConst;
import com.yzchn.platform.common.annotation.OperLog;
import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.model.dto.TMenuDTO;
import com.yzchn.platform.model.vo.TMenuNodeVO;
import com.yzchn.platform.service.TMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 菜单表(TMenu)表控制层
 *
 * @author songyuan
 * @since 2021-06-23 11:04:44
 */
@RestController
@RequestMapping("tMenu")
public class TMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TMenuService tMenuService;


    /**
     * @param tMenudto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-23 11:04:44
     */
    @PostMapping("/findTMenuSelectList")
    public Response findTMenuSelectList(@RequestBody TMenuDTO tMenudto) {
        return Response.ok(BusinessEnum.SUCCESS, tMenuService.findTMenuSelectList(tMenudto));
    }

    /**
     * @param tMenudto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有二级数据
     * @date 2021-06-23 11:04:44
     */
    @PostMapping("/findSecondaryMenuSelectList")
    public Response findSecondaryMenuSelectList(@RequestBody TMenuDTO tMenudto) {
        return Response.ok(BusinessEnum.SUCCESS, tMenuService.findSecondaryMenuSelectList(tMenudto));
    }

    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-23 11:04:44
     */
    @GetMapping("/selectTMenuById/{id}")
    public Response selectTMenuById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tMenuService.selectTMenuById(id));
    }

    /**
     * @param tMenu 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:04:44
     */
    @PostMapping
    public Response insert(@RequestBody TMenuDTO tMenu) {
        tMenuService.insert(tMenu);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tMenu 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-23 11:04:44
     */
    @PutMapping
    public Response update(@RequestBody TMenuDTO tMenu) {
        return Response.ok(BusinessEnum.SUCCESS, tMenuService.update(tMenu));
    }

    /**
     * @param idList 主键集合
     * @return 删除结果
     * @author 洛无异
     * @description //TODO  删除数据
     * @date 2021-06-23 11:04:44
     */
    @DeleteMapping
    public Response delete(@RequestParam("idList") List<Long> idList) {
        tMenuService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 菜单分配树级查询列表
     * @date 2021/6/23 15:09
     */
    @GetMapping("/getMenuList")
    public Response getMenuList() {
        return Response.ok(BusinessEnum.SUCCESS, tMenuService.getMenuNodeList());
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得父级别菜单
     * @date 2021/6/23 15:09
     */
    @GetMapping("/getMenuSelect")
    @OperLog(operModul = "父级别菜单下拉查询", operType = OprLogConst.SELECT, operDesc = "菜单下拉条件框")
    public Response getMenuSelect(@RequestParam("pid") Long pid) {
        return Response.ok(BusinessEnum.SUCCESS, tMenuService.getMenuSelect(pid));
    }

}
