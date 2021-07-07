package com.yzchn.platform.controller;


import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.model.dto.TUserRoleRelationDTO;
import com.yzchn.platform.service.TUserRoleRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关联表(TUserRoleRelation)表控制层
 *
 * @author songyuan
 * @since 2021-06-23 11:08:06
 */
@RestController
@RequestMapping("tUserRoleRelation")
public class TUserRoleRelationController {
    /**
     * 服务对象
     */
    @Resource
    private TUserRoleRelationService tUserRoleRelationService;


    /**
     * @param tUserRoleRelationdto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-23 11:08:06
     */
    @PostMapping("/findTUserRoleRelationSelectList")
    public Response findTUserRoleRelationSelectList(@RequestBody TUserRoleRelationDTO tUserRoleRelationdto) {
        return Response.ok(BusinessEnum.SUCCESS, tUserRoleRelationService.findTUserRoleRelationSelectList(tUserRoleRelationdto));
    }


    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-23 11:08:06
     */
    @GetMapping("/selectTUserRoleRelationById/{id}")
    public Response selectTUserRoleRelationById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tUserRoleRelationService.selectTUserRoleRelationById(id));
    }

    /**
     * @param tUserRoleRelation 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:08:06
     */
    @PostMapping
    public Response insert(@RequestBody TUserRoleRelationDTO tUserRoleRelation) {
        tUserRoleRelationService.insert(tUserRoleRelation);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tUserRoleRelation 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-23 11:08:06
     */
    @PutMapping
    public Response update(@RequestBody TUserRoleRelationDTO tUserRoleRelation) {
        return Response.ok(BusinessEnum.SUCCESS, tUserRoleRelationService.update(tUserRoleRelation));
    }

    /**
     * @param idList 主键集合
     * @return 删除结果
     * @author 洛无异
     * @description //TODO  删除数据
     * @date 2021-06-23 11:08:06
     */
    @DeleteMapping
    public Response delete(@RequestParam("idList") List<Long> idList) {
        tUserRoleRelationService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }
    /**
     * @param tUserRoleRelation
     * @return void
     * @author 洛无异
     * @description //TODO     给用户分配角色
     * @date 2021/6/23 14:49
     */
    @PostMapping("/assigningRoles")
    public Response assigningRoles(@RequestBody TUserRoleRelationDTO tUserRoleRelation) {
        tUserRoleRelationService.assigningRoles(tUserRoleRelation);
        return Response.ok(BusinessEnum.SUCCESS);
    }


}
