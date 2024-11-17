package com.zy.mallmember.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 会员登录记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@TableName("ums_member_login_log")
public class MemberLoginLogEntity {

    /**
     * id
     */
	private Long id;
    /**
     * member_id
     */
	private Long memberId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * ip
     */
	private String ip;
    /**
     * city
     */
	private String city;
    /**
     * 登录类型[1-web，2-app]
     */
	private Integer loginType;
}