package com.zy.mallcoupon.dao;

//import com.zy.mallcoupon.dao.BaseDao;
//import com.zy.modules.mallcoupon.entity.HomeAdvEntity;
//import org.apache.ibatis.annotations.Mapper;

import com.zy.mallcoupon.entity.HomeAdvEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页轮播广告
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface HomeAdvDao extends BaseDao<HomeAdvEntity> {
	
}