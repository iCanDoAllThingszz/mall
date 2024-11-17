package com.zy.mallmember.controller;


import com.zy.mallmember.dto.GrowthChangeHistoryDTO;
import com.zy.mallmember.excel.GrowthChangeHistoryExcel;
import com.zy.mallmember.service.GrowthChangeHistoryService;
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
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 成长值变化历史记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallmember/growthchangehistory")
@Tag(name="成长值变化历史记录")
public class GrowthChangeHistoryController {
    @Autowired
    private GrowthChangeHistoryService growthChangeHistoryService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallmember:growthchangehistory:page")
    public Result<PageData<GrowthChangeHistoryDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<GrowthChangeHistoryDTO> page = growthChangeHistoryService.page(params);

        return new Result<PageData<GrowthChangeHistoryDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallmember:growthchangehistory:info")
    public Result<GrowthChangeHistoryDTO> get(@PathVariable("id") Long id){
        GrowthChangeHistoryDTO data = growthChangeHistoryService.get(id);

        return new Result<GrowthChangeHistoryDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallmember:growthchangehistory:save")
    public Result save(@RequestBody GrowthChangeHistoryDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        growthChangeHistoryService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallmember:growthchangehistory:update")
    public Result update(@RequestBody GrowthChangeHistoryDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        growthChangeHistoryService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallmember:growthchangehistory:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        growthChangeHistoryService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallmember:growthchangehistory:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<GrowthChangeHistoryDTO> list = growthChangeHistoryService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "成长值变化历史记录", list, GrowthChangeHistoryExcel.class);
    }

}
