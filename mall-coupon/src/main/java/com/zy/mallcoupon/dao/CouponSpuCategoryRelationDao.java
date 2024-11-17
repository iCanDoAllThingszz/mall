package com.zy.mallcoupon.dao;

//import com.zy.mallcoupon.dao.BaseDao;
//import com.zy.modules.mallcoupon.entity.CouponSpuCategoryRelationEntity;
//import org.apache.ibatis.annotations.Mapper;

import com.zy.mallcoupon.entity.CouponSpuCategoryRelationEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券分类关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface CouponSpuCategoryRelationDao extends BaseDao<CouponSpuCategoryRelationEntity> {
	
}