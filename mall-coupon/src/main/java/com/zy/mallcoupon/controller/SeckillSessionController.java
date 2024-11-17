package com.zy.mallcoupon.controller;

//import com.zy.mallcoupon.annotation.LogOperation;
//import com.zy.mallcoupon.constant.Constant;
//import com.zy.mallcoupon.page.PageData;
//import com.zy.mallcoupon.utils.ExcelUtils;
//import com.zy.mallcoupon.utils.Result;
//import com.zy.mallcoupon.validator.AssertUtils;
//import com.zy.mallcoupon.validator.ValidatorUtils;
//import com.zy.mallcoupon.validator.group.AddGroup;
//import com.zy.mallcoupon.validator.group.DefaultGroup;
//import com.zy.mallcoupon.validator.group.UpdateGroup;
//import com.zy.modules.mallcoupon.dto.SeckillSessionDTO;
//import com.zy.modules.mallcoupon.excel.SeckillSessionExcel;
//import com.zy.modules.mallcoupon.service.SeckillSessionService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.Parameters;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;


import com.zy.mallcoupon.dto.SeckillSessionDTO;
import com.zy.mallcoupon.excel.SeckillSessionExcel;
import com.zy.mallcoupon.service.SeckillSessionService;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallcoupon/seckillsession")
@Tag(name="秒杀活动场次")
public class SeckillSessionController {
    @Autowired
    private SeckillSessionService seckillSessionService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallcoupon:seckillsession:page")
    public Result<PageData<SeckillSessionDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<SeckillSessionDTO> page = seckillSessionService.page(params);

        return new Result<PageData<SeckillSessionDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallcoupon:seckillsession:info")
    public Result<SeckillSessionDTO> get(@PathVariable("id") Long id){
        SeckillSessionDTO data = seckillSessionService.get(id);

        return new Result<SeckillSessionDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallcoupon:seckillsession:save")
    public Result save(@RequestBody SeckillSessionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        seckillSessionService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallcoupon:seckillsession:update")
    public Result update(@RequestBody SeckillSessionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        seckillSessionService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallcoupon:seckillsession:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        seckillSessionService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallcoupon:seckillsession:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SeckillSessionDTO> list = seckillSessionService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "秒杀活动场次", list, SeckillSessionExcel.class);
    }

}
