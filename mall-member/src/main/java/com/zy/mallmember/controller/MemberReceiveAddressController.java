package com.zy.mallmember.controller;

//import com.zy.common.annotation.LogOperation;
//import com.zy.common.constant.Constant;
//import com.zy.common.page.PageData;
//import com.zy.common.utils.ExcelUtils;
//import com.zy.common.utils.Result;
//import com.zy.common.validator.AssertUtils;
//import com.zy.common.validator.ValidatorUtils;
//import com.zy.common.validator.group.AddGroup;
//import com.zy.common.validator.group.DefaultGroup;
//import com.zy.common.validator.group.UpdateGroup;
import com.zy.mallmember.dto.MemberReceiveAddressDTO;
import com.zy.mallmember.excel.MemberReceiveAddressExcel;
import com.zy.mallmember.service.MemberReceiveAddressService;
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
 * 会员收货地址
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@RestController
@RequestMapping("mallmember/memberreceiveaddress")
@Tag(name="会员收货地址")
public class MemberReceiveAddressController {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    //@RequiresPermissions("mallmember:memberreceiveaddress:page")
    public Result<PageData<MemberReceiveAddressDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<MemberReceiveAddressDTO> page = memberReceiveAddressService.page(params);

        return new Result<PageData<MemberReceiveAddressDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("mallmember:memberreceiveaddress:info")
    public Result<MemberReceiveAddressDTO> get(@PathVariable("id") Long id){
        MemberReceiveAddressDTO data = memberReceiveAddressService.get(id);

        return new Result<MemberReceiveAddressDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("mallmember:memberreceiveaddress:save")
    public Result save(@RequestBody MemberReceiveAddressDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        memberReceiveAddressService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallmember:memberreceiveaddress:update")
    public Result update(@RequestBody MemberReceiveAddressDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        memberReceiveAddressService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("mallmember:memberreceiveaddress:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        memberReceiveAddressService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("mallmember:memberreceiveaddress:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<MemberReceiveAddressDTO> list = memberReceiveAddressService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "会员收货地址", list, MemberReceiveAddressExcel.class);
    }

}
