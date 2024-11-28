package com.zy.mallproduct.controller;

import com.zy.mallproduct.dto.AttrDTO;
import com.zy.mallproduct.excel.AttrExcel;
import com.zy.mallproduct.service.AttrService;
import com.zy.mallproduct.vo.AttrVO;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 商品属性
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallproduct/attr")
@Tag(name="商品属性")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallproduct:attr:page")
    public Result<PageData<AttrDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<AttrDTO> page = attrService.page(params);

        List<AttrDTO> attrDTOS = page.getList();

        //查询每一条规格参数对应的类别名称和属性组名称
        List<AttrDTO> collect = attrDTOS.stream().map(attrDTO -> attrService.infoSupply(attrDTO)).collect(Collectors.toList());
        page.setList(collect);

        return new Result<PageData<AttrDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallproduct:attr:info")
    public Result<AttrDTO> get(@PathVariable("id") Long id){
        AttrDTO data = attrService.get(id);

        return new Result<AttrDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallproduct:attr:save")
    public Result save(@RequestBody AttrVO attrvo){
        //效验数据
        ValidatorUtils.validateEntity(attrvo, AddGroup.class, DefaultGroup.class);

        //attrService.save(dto);

        //保存规格参数的同时保存 规则参数 - 属性分组 的映射关系
        attrService.saveAttr(attrvo);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallproduct:attr:update")
    public Result update(@RequestBody AttrDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        attrService.updateCascade(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallproduct:attr:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        //attrService.delete(ids);
        attrService.deleteCascade(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallproduct:attr:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AttrDTO> list = attrService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "商品属性", list, AttrExcel.class);
    }

}
