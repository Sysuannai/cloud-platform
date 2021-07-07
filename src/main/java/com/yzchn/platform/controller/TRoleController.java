package com.yzchn.platform.controller;


import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.model.dto.TRoleDTO;
import com.yzchn.platform.service.TRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(TRole)表控制层
 *
 * @author songyuan
 * @since 2021-06-22 16:26:19
 */
@RestController
@RequestMapping("tRole")
public class TRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleService tRoleService;


    /**
     * @param tRoledto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-22 16:26:19
     */
    @PostMapping("/findRoleSelectList")
    public Response findTRoleSelectList(@RequestBody TRoleDTO tRoledto) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleService.findTRoleSelectList(tRoledto));
    }


    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-22 16:26:19
     */
    @GetMapping("/selectTRoleById/{id}")
    public Response selectTRoleById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleService.selectTRoleById(id));
    }

    /**
     * @param tRole 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-22 16:26:19
     */
    @PostMapping
    public Response insert(@RequestBody TRoleDTO tRole) {
        tRoleService.insert(tRole);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tRole 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-22 16:26:19
     */
    @PutMapping
    public Response update(@RequestBody TRoleDTO tRole) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleService.update(tRole));
    }

    /**
     * @param idList 主键集合
     * @return 删除结果
     * @author 洛无异
     * @description //TODO  删除数据
     * @date 2021-06-22 16:26:19
     */
    @DeleteMapping
    public Response delete(@RequestParam("idList") List<Long> idList) {
        tRoleService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }
    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO  获得角色下拉列表
     * @date 2021/6/23 16:07
     */
    @GetMapping("/getRoleSelect")
    public Response getRoleSelect() {
        return Response.ok(BusinessEnum.SUCCESS,tRoleService.getRoleSelect());
    }
}
