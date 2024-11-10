package com.zy.mallcoupon.dao;

import com.zy.mallcoupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 12:07:17
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
