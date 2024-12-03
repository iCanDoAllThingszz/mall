package com.zy.mallware.controller;

import com.zy.mallware.dto.PurchaseDTO;
import com.zy.mallware.dto.WareOrderTaskDTO;
import com.zy.mallware.excel.PurchaseExcel;
import com.zy.mallware.service.PurchaseService;
import com.zy.mallware.service.WareOrderTaskService;
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


/**
 * 采购信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallware/purchase")
@Tag(name="采购信息")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private WareOrderTaskService wareOrderTaskService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallware:purchase:page")
    public Result<PageData<PurchaseDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<PurchaseDTO> page = purchaseService.page(params);

        return new Result<PageData<PurchaseDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallware:purchase:info")
    public Result<PurchaseDTO> get(@PathVariable("id") Long id){
        PurchaseDTO data = purchaseService.get(id);

        return new Result<PurchaseDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallware:purchase:save")
    public Result save(@RequestBody PurchaseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        purchaseService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallware:purchase:update")
    public Result update(@RequestBody PurchaseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        purchaseService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallware:purchase:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        purchaseService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallware:purchase:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PurchaseDTO> list = purchaseService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "采购信息", list, PurchaseExcel.class);
    }

    /**
     * 查询所有状态为新建/已分配的采购单信息
     * */
    @GetMapping("/page/newWareOrder")
    @Operation(summary = "查询所有状态为新建/已分配的采购单信息")
    @LogOperation("查询所有状态为新建/已分配的采购单信息")
    public Result<List<WareOrderTaskDTO>> pageNewWareOrder(@RequestParam Map<String, Object> params){
        PageData<WareOrderTaskDTO> page = wareOrderTaskService.page(params);

        return new Result<List<WareOrderTaskDTO>>().ok(page.getList());
    }

    /**
     * 将采购需求合并到某个采购单中, 或者新建采购单
     * */
    @GetMapping("/page/newWareOrder")
    @Operation(summary = "查询所有状态为新建/已分配的采购单信息")
    @LogOperation("查询所有状态为新建/已分配的采购单信息")
    public Result<WareOrderTaskDTO> mergeOrAddWareTaskOrder(@RequestBody List<PurchaseDTO> purchaseDTOS, @RequestBody Boolean merge, @RequestBody String wareTaskOrderId){
        WareOrderTaskDTO wareOrderTaskDTO = purchaseService.mergeOrAddWareTaskOrder(purchaseDTOS, merge, wareTaskOrderId);

        return new Result<WareOrderTaskDTO>().ok(wareOrderTaskDTO);
    }

}
