package com.zy.mallware.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("wms_purchase")
public class PurchaseEntity {

    /**
     * 采购单id
     */
	private Long id;
    /**
     * 采购人id
     */
	private Long assigneeId;
    /**
     * 采购人名
     */
	private String assigneeName;
    /**
     * 联系方式
     */
	private String phone;
    /**
     * 优先级
     */
	private Integer priority;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 仓库id
     */
	private Long wareId;
    /**
     * 总金额
     */
	private BigDecimal amount;
    /**
     * 创建日期
     */
	private Date createTime;
    /**
     * 更新日期
     */
	private Date updateTime;
}