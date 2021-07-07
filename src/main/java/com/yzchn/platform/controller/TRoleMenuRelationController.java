package com.yzchn.platform.controller;


import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.model.dto.TRoleMenuRelationDTO;
import com.yzchn.platform.service.TRoleMenuRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色菜单关联表(TRoleMenuRelation)表控制层
 *
 * @author songyuan
 * @since 2021-06-23 11:08:05
 */
@RestController
@RequestMapping("tRoleMenuRelation")
public class TRoleMenuRelationController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleMenuRelationService tRoleMenuRelationService;


    /**
     * @param tRoleMenuRelationdto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-23 11:08:05
     */
    @PostMapping("/findTRoleMenuRelationSelectList")
    public Response findTRoleMenuRelationSelectList(@RequestBody TRoleMenuRelationDTO tRoleMenuRelationdto) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleMenuRelationService.findTRoleMenuRelationSelectList(tRoleMenuRelationdto));
    }


    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-23 11:08:05
     */
    @GetMapping("/selectTRoleMenuRelationById/{id}")
    public Response selectTRoleMenuRelationById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleMenuRelationService.selectTRoleMenuRelationById(id));
    }

    /**
     * @param tRoleMenuRelation 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:08:05
     */
    @PostMapping
    public Response insert(@RequestBody TRoleMenuRelationDTO tRoleMenuRelation) {
        tRoleMenuRelationService.insert(tRoleMenuRelation);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tRoleMenuRelation 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-23 11:08:05
     */
    @PutMapping
    public Response update(@RequestBody TRoleMenuRelationDTO tRoleMenuRelation) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleMenuRelationService.update(tRoleMenuRelation));
    }

    /**
     * @param idList 主键集合
     * @return 删除结果
     * @author 洛无异
     * @description //TODO  删除数据
     * @date 2021-06-23 11:08:05
     */
    @DeleteMapping
    public Response delete(@RequestParam("idList") List<Long> idList) {
        tRoleMenuRelationService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tRoleMenuRelation
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO       给角色分配菜单
     * @date 2021/6/23 15:28
     */
    @PostMapping("/allocMenu")
    public Response allocMenu(@RequestBody TRoleMenuRelationDTO tRoleMenuRelation) {
        if (StringUtils.isObjectEmpty(tRoleMenuRelation.getRoleId())) {
            throw new BusinessException(BusinessEnum.ROLE_ID_IS_NULL);
        }
        tRoleMenuRelationService.allocMenu(tRoleMenuRelation);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO   获得所有选中菜单的id
     * @date 2021/6/23 15:33
     */
    @GetMapping("/getMenuIds")
    public Response getMenuIds(@RequestParam("roleId") Long roleId) {
        if (StringUtils.isObjectEmpty(roleId)) {
            throw new BusinessException(BusinessEnum.ROLE_ID_IS_NULL);
        }
        return Response.ok(BusinessEnum.SUCCESS, tRoleMenuRelationService.getMenuIds(roleId));
    }

}
