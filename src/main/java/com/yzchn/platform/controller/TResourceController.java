package com.yzchn.platform.controller;


import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.model.dto.TResourceDTO;
import com.yzchn.platform.model.entity.TResource;
import com.yzchn.platform.model.vo.TResourceVO;
import com.yzchn.platform.service.TResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.lang.Long;

/**
 * 系统资源表(TResource)表控制层
 *
 * @author songyuan
 * @since 2021-06-23 16:38:54
 */
@RestController
@RequestMapping("tResource")
public class TResourceController {
    /**
     * 服务对象
     */
    @Resource
    private TResourceService tResourceService;


    /**
     * @param tResourcedto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-23 16:38:54
     */
    @PostMapping("/findTResourceSelectList")
    public Response findTResourceSelectList(@RequestBody TResourceDTO tResourcedto) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceService.findTResourceSelectList(tResourcedto));
    }


    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-23 16:38:54
     */
    @GetMapping("/selectTResourceById/{id}")
    public Response selectTResourceById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceService.selectTResourceById(id));
    }

    /**
     * @param tResource 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 16:38:54
     */
    @PostMapping
    public Response insert(@RequestBody TResourceDTO tResource) {
        tResourceService.insert(tResource);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tResource 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-23 16:38:54
     */
    @PutMapping
    public Response update(@RequestBody TResourceDTO tResource) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceService.update(tResource));
    }

    /**
     * @param idList 主键集合
     * @return 删除结果
     * @author 洛无异
     * @description //TODO  删除数据
     * @date 2021-06-23 16:38:54
     */
    @DeleteMapping
    public Response delete(@RequestParam("idList") List<Long> idList) {
        tResourceService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得所有的资源信息
     * @date 2021/6/24 15:56
     */
    @GetMapping("/findResource")
    public Response findResource() {
        return Response.ok(BusinessEnum.SUCCESS, tResourceService.findResource());
    }

    /**
     * @param roleId 角色Id
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO   获得选中的资源信息
     * @date 2021/6/24 16:02
     */
    @GetMapping("/findSelectedResource")
    public Response findSelectedResource(@RequestParam("roleId") Long roleId) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceService.findSelectedResource(roleId));
    }
}
