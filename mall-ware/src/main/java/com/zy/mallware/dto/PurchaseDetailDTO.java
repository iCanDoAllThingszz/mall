package com.zy.mallware.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@Schema(name = "")
public class PurchaseDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "")
	private Long id;

	@SchemaProperty(name = "采购单id")
	private Long purchaseId;

	@SchemaProperty(name = "采购商品id")
	private Long skuId;

	@SchemaProperty(name = "采购数量")
	private Integer skuNum;

	@SchemaProperty(name = "采购金额")
	private BigDecimal skuPrice;

	@SchemaProperty(name = "仓库id")
	private Long wareId;

	@SchemaProperty(name = "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
	private Integer status;


}