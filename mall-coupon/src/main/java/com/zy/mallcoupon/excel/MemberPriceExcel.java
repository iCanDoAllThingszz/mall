package com.zy.mallcoupon.excel;

//import com.alibaba.excel.annotation.ExcelProperty;
//import com.alibaba.excel.annotation.write.style.ColumnWidth;
//import com.alibaba.excel.annotation.write.style.ContentRowHeight;
//import com.alibaba.excel.annotation.write.style.HeadRowHeight;
//import lombok.Data;

//import java.math.BigDecimal;
//import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品会员价格
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
public class MemberPriceExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "sku_id")
    private Long skuId;
    @ExcelProperty(value = "会员等级id")
    private Long memberLevelId;
    @ExcelProperty(value = "会员等级名")
    private String memberLevelName;
    @ExcelProperty(value = "会员对应价格")
    private BigDecimal memberPrice;
    @ExcelProperty(value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
    private Integer addOther;

}