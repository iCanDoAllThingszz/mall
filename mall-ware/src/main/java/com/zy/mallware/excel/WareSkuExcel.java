package com.zy.mallware.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 商品库存
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
public class WareSkuExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "sku_id")
    private Long skuId;
    @ExcelProperty(value = "仓库id")
    private Long wareId;
    @ExcelProperty(value = "库存数")
    private Integer stock;
    @ExcelProperty(value = "sku_name")
    private String skuName;
    @ExcelProperty(value = "锁定库存")
    private Integer stockLocked;

}