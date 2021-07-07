package com.yzchn.platform.controller;


import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.model.dto.TRoleResourceRelationDTO;
import com.yzchn.platform.service.TRoleResourceRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色资源关联表(TRoleResourceRelation)表控制层
 *
 * @author songyuan
 * @since 2021-06-23 11:08:06
 */
@RestController
@RequestMapping("tRoleResourceRelation")
public class TRoleResourceRelationController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleResourceRelationService tRoleResourceRelationService;


    /**
     * @param tRoleResourceRelationdto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-23 11:08:06
     */
    @PostMapping("/findTRoleResourceRelationSelectList")
    public Response findTRoleResourceRelationSelectList(@RequestBody TRoleResourceRelationDTO tRoleResourceRelationdto) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleResourceRelationService.findTRoleResourceRelationSelectList(tRoleResourceRelationdto));
    }


    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-23 11:08:06
     */
    @GetMapping("/selectTRoleResourceRelationById/{id}")
    public Response selectTRoleResourceRelationById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleResourceRelationService.selectTRoleResourceRelationById(id));
    }

    /**
     * @param tRoleResourceRelation 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:08:06
     */
    @PostMapping
    public Response insert(@RequestBody TRoleResourceRelationDTO tRoleResourceRelation) {
        tRoleResourceRelationService.insert(tRoleResourceRelation);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tRoleResourceRelation 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-23 11:08:06
     */
    @PutMapping
    public Response update(@RequestBody TRoleResourceRelationDTO tRoleResourceRelation) {
        return Response.ok(BusinessEnum.SUCCESS, tRoleResourceRelationService.update(tRoleResourceRelation));
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
        tRoleResourceRelationService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tRoleResourceRelation
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 给角色分配资源信息
     * @date 2021/6/24 13:33
     */
    @PostMapping("/allocResource")
    public Response allocResource(@RequestBody TRoleResourceRelationDTO tRoleResourceRelation) {
        if (StringUtils.isObjectEmpty(tRoleResourceRelation.getRoleId())) {
            throw new BusinessException(BusinessEnum.ROLE_ID_IS_NULL);
        }
        tRoleResourceRelationService.allocResource(tRoleResourceRelation);
        return Response.ok(BusinessEnum.SUCCESS);
    }
}
