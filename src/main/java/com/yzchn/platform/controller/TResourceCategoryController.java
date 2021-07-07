package com.yzchn.platform.controller;


import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.model.dto.TResourceCategoryDTO;
import com.yzchn.platform.model.entity.TResourceCategory;
import com.yzchn.platform.model.vo.TResourceCategoryVO;
import com.yzchn.platform.service.TResourceCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资源分类表(TResourceCategory)表控制层
 *
 * @author songyuan
 * @since 2021-06-23 11:08:04
 */
@RestController
@RequestMapping("tResourceCategory")
public class TResourceCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private TResourceCategoryService tResourceCategoryService;


    /**
     * @param tResourceCategorydto 请求参数封装
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询所有数据
     * @date 2021-06-23 11:08:04
     */
    @PostMapping("/findTResourceCategorySelectList")
    public Response findTResourceCategorySelectList(@RequestBody TResourceCategoryDTO tResourceCategorydto) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceCategoryService.findTResourceCategorySelectList(tResourceCategorydto));
    }


    /**
     * @param id 主键
     * @return 单条数据
     * @author 洛无异
     * @description //TODO  通过主键查询单条数据
     * @date 2021-06-23 11:08:04
     */
    @GetMapping("/selectTResourceCategoryById/{id}")
    public Response selectTResourceCategoryById(@PathVariable Long id) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceCategoryService.selectTResourceCategoryById(id));
    }

    /**
     * @param tResourceCategory 实体对象
     * @return 新增结果
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-23 11:08:04
     */
    @PostMapping
    public Response insert(@RequestBody TResourceCategoryDTO tResourceCategory) {
        tResourceCategoryService.insert(tResourceCategory);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param tResourceCategory 实体对象
     * @return 修改结果
     * @author 洛无异
     * @description //TODO  修改数据
     * @date 2021-06-23 11:08:04
     */
    @PutMapping
    public Response update(@RequestBody TResourceCategoryDTO tResourceCategory) {
        return Response.ok(BusinessEnum.SUCCESS, tResourceCategoryService.update(tResourceCategory));
    }

    /**
     * @param idList 主键集合
     * @return 删除结果
     * @author 洛无异
     * @description //TODO  删除数据
     * @date 2021-06-23 11:08:04
     */
    @DeleteMapping
    public Response delete(@RequestParam("idList") List<Long> idList) {
        tResourceCategoryService.deleteById(idList);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得资源分类下拉列表
     * @date 2021/6/23 16:33
     */
    @GetMapping("/categorySelect")
    public Response categorySelect() {
        return Response.ok(BusinessEnum.SUCCESS, tResourceCategoryService.categorySelect());
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO        获得分类信息
     * @date 2021/6/24 15:48
     */
    @GetMapping("/findCategoryList")
    public Response findCategoryList() {
        return Response.ok(BusinessEnum.SUCCESS, tResourceCategoryService.findCategoryList());
    }
}
