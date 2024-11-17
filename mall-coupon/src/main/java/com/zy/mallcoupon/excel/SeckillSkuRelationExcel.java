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
 * 秒杀活动商品关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
public class SeckillSkuRelationExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "活动id")
    private Long promotionId;
    @ExcelProperty(value = "活动场次id")
    private Long promotionSessionId;
    @ExcelProperty(value = "商品id")
    private Long skuId;
    @ExcelProperty(value = "秒杀价格")
    private BigDecimal seckillPrice;
    @ExcelProperty(value = "秒杀总量")
    private BigDecimal seckillCount;
    @ExcelProperty(value = "每人限购数量")
    private BigDecimal seckillLimit;
    @ExcelProperty(value = "排序")
    private Integer seckillSort;

}