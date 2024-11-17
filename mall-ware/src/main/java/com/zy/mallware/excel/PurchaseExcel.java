package com.zy.mallware.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
public class PurchaseExcel {
    @ExcelProperty(value = "采购单id")
    private Long id;
    @ExcelProperty(value = "采购人id")
    private Long assigneeId;
    @ExcelProperty(value = "采购人名")
    private String assigneeName;
    @ExcelProperty(value = "联系方式")
    private String phone;
    @ExcelProperty(value = "优先级")
    private Integer priority;
    @ExcelProperty(value = "状态")
    private Integer status;
    @ExcelProperty(value = "仓库id")
    private Long wareId;
    @ExcelProperty(value = "总金额")
    private BigDecimal amount;
    @ExcelProperty(value = "创建日期")
    private Date createTime;
    @ExcelProperty(value = "更新日期")
    private Date updateTime;

}