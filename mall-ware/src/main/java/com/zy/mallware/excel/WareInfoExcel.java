package com.zy.mallware.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 仓库信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
public class WareInfoExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "仓库名")
    private String name;
    @ExcelProperty(value = "仓库地址")
    private String address;
    @ExcelProperty(value = "区域编码")
    private String areacode;

}