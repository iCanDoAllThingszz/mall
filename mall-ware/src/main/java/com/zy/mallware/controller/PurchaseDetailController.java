package com.zy.mallware.controller;

import com.zy.mallware.dto.PurchaseDetailDTO;
import com.zy.mallware.excel.PurchaseDetailExcel;
import com.zy.mallware.service.PurchaseDetailService;
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
 * 
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallware/purchasedetail")
@Tag(name="")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallware:purchasedetail:page")
    public Result<PageData<PurchaseDetailDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<PurchaseDetailDTO> page = purchaseDetailService.page(params);

        return new Result<PageData<PurchaseDetailDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallware:purchasedetail:info")
    public Result<PurchaseDetailDTO> get(@PathVariable("id") Long id){
        PurchaseDetailDTO data = purchaseDetailService.get(id);

        return new Result<PurchaseDetailDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallware:purchasedetail:save")
    public Result save(@RequestBody PurchaseDetailDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        purchaseDetailService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallware:purchasedetail:update")
    public Result update(@RequestBody PurchaseDetailDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        purchaseDetailService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallware:purchasedetail:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        purchaseDetailService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallware:purchasedetail:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PurchaseDetailDTO> list = purchaseDetailService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "", list, PurchaseDetailExcel.class);
    }

}
