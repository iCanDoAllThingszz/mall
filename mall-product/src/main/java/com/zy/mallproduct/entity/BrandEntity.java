package com.zy.mallproduct.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@TableName("pms_brand")
public class BrandEntity {

    /**
     * 品牌id
     */

    @TableId(type = IdType.AUTO)
	private Long brandId;

    /**
     * 品牌名
     */
    //@NotEmpty 不能为null/""
    //@NotBlank 不能为null/""/" " (必须包含一个非空格字符)
    @NotNull // 不能为null
	private String name;

    /**
     * 品牌logo地址
     */
	private String logo;
    /**
     * 介绍
     */
	private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
	private Integer showStatus;
    /**
     * 检索首字母
     */
	private String firstLetter;
    /**
     * 排序
     */
	private Integer sort;
}