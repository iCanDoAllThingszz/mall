package com.zy.mallmember.dao;


import com.zy.mallmember.entity.MemberStatisticsInfoEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员统计信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface MemberStatisticsInfoDao extends BaseDao<MemberStatisticsInfoEntity> {
	
}