package com.zy.mallcoupon.dao;


//import com.zy.mallcoupon.entity.CouponHistoryEntity;
//import io.renren.common.dao.BaseDao;
//import org.apache.ibatis.annotations.Mapper;

import com.zy.mallcoupon.entity.CouponHistoryEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface CouponHistoryDao extends BaseDao<CouponHistoryEntity> {
	
}