package com.zy.mallorder.controller;

import com.zy.mallorder.dto.OrderSettingDTO;
import com.zy.mallorder.excel.OrderSettingExcel;
import com.zy.mallorder.service.OrderSettingService;
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
 * 订单配置信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallorder/ordersetting")
@Tag(name="订单配置信息")
public class OrderSettingController {
    @Autowired
    private OrderSettingService orderSettingService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallorder:ordersetting:page")
    public Result<PageData<OrderSettingDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<OrderSettingDTO> page = orderSettingService.page(params);

        return new Result<PageData<OrderSettingDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallorder:ordersetting:info")
    public Result<OrderSettingDTO> get(@PathVariable("id") Long id){
        OrderSettingDTO data = orderSettingService.get(id);

        return new Result<OrderSettingDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallorder:ordersetting:save")
    public Result save(@RequestBody OrderSettingDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        orderSettingService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallorder:ordersetting:update")
    public Result update(@RequestBody OrderSettingDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        orderSettingService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallorder:ordersetting:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        orderSettingService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallorder:ordersetting:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<OrderSettingDTO> list = orderSettingService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "订单配置信息", list, OrderSettingExcel.class);
    }

}
