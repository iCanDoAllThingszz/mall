package com.zy.mallproduct.controller;

import com.zy.mallproduct.dto.BrandDTO;
import com.zy.mallproduct.excel.BrandExcel;
import com.zy.mallproduct.service.BrandService;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.exception.UpdateGroups;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallproduct/brand")
@Tag(name="品牌")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallproduct:brand:page")
    public Result<PageData<BrandDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<BrandDTO> page = brandService.page(params);

        return new Result<PageData<BrandDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallproduct:brand:info")
    public Result<BrandDTO> get(@PathVariable("id") Long id){
        BrandDTO data = brandService.get(id);

        return new Result<BrandDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallproduct:brand:save")
    //jakarta.validation-api 关注于定义验证规范和注解，而 spring-boot-starter-validation 关注于在 Spring Boot 应用程序中自动配置和集成验证功能。
    public Result save(@Valid @RequestBody BrandDTO dto, BindingResult result){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        if(result.hasErrors()){
            return new Result().error(400, result.getAllErrors().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        brandService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallproduct:brand:update")
    public Result update(@Validated({UpdateGroups.class}) @RequestBody BrandDTO dto, BindingResult result){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        //JSR303校验出了非法字段
        if (result.hasErrors()) {
            return new Result().error(400, result.getAllErrors().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        //brandService.update(dto);

        //更新brand表的同时, 更新brand-category关系表中的brand_name
        brandService.updateCascade(dto);

        return new Result();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallproduct:brand:delete")
    public Result delete(@RequestBody Long[] ids){

        brandService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallproduct:brand:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BrandDTO> list = brandService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "品牌", list, BrandExcel.class);
    }

}
