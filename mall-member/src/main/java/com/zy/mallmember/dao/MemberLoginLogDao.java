package com.zy.mallmember.dao;


import com.zy.mallmember.entity.MemberLoginLogEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface MemberLoginLogDao extends BaseDao<MemberLoginLogEntity> {
	
}