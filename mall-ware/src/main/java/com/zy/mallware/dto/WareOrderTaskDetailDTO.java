package com.zy.mallware.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 库存工作单
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@Schema(name = "库存工作单")
public class WareOrderTaskDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "sku_id")
	private Long skuId;

	@SchemaProperty(name = "sku_name")
	private String skuName;

	@SchemaProperty(name = "购买个数")
	private Integer skuNum;

	@SchemaProperty(name = "工作单id")
	private Long taskId;


}
