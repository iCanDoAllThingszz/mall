package com.zy.mallproduct.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;


/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@Schema(name = "品牌")
public class BrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "品牌id")
	private Long brandId;

	@SchemaProperty(name = "品牌名")
	@NotEmpty(message = "品牌名称字段不能为空")
	private String name;

	@SchemaProperty(name = "品牌logo地址")
	@URL(message = "logo的格式必须是url合法地址")
	@NotEmpty(message = "logo字段不能为空")
	private String logo;

	@SchemaProperty(name = "介绍")
	private String descript;

	@SchemaProperty(name = "显示状态[0-不显示；1-显示]")
	private Integer showStatus = 1;

	@SchemaProperty(name = "检索首字母")
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是单个字母")
	@NotEmpty(message = "检索首字母字段不能为空")
	private String firstLetter;

	@SchemaProperty(name = "排序")
	@Min(value = 0, message = "排序字段不能小于0")
	@Max(value = 9999, message = "排序字段不能大于9999")
	private Integer sort = 0;
}

