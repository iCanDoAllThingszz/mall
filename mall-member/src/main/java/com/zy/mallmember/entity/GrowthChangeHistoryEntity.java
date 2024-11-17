package com.zy.mallmember.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 成长值变化历史记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@TableName("ums_growth_change_history")
public class GrowthChangeHistoryEntity {

    /**
     * id
     */
	private Long id;
    /**
     * member_id
     */
	private Long memberId;
    /**
     * create_time
     */
	private Date createTime;
    /**
     * 改变的值（正负计数）
     */
	private Integer changeCount;
    /**
     * 备注
     */
	private String note;
    /**
     * 积分来源[0-购物，1-管理员修改]
     */
	private Integer sourceType;
}