package com.zy.mallware.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 采购信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@Schema(name = "采购信息")
public class PurchaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "采购单id")
	private Long id;

	@SchemaProperty(name = "采购人id")
	private Long assigneeId;

	@SchemaProperty(name = "采购人名")
	private String assigneeName;

	@SchemaProperty(name = "联系方式")
	private String phone;

	@SchemaProperty(name = "优先级")
	private Integer priority;

	@SchemaProperty(name = "状态")
	private Integer status;

	@SchemaProperty(name = "仓库id")
	private Long wareId;

	@SchemaProperty(name = "总金额")
	private BigDecimal amount;

	@SchemaProperty(name = "创建日期")
	private Date createTime;

	@SchemaProperty(name = "更新日期")
	private Date updateTime;


}
