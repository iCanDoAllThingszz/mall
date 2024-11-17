package com.zy.mallcoupon.dao;

//import com.zy.mallcoupon.dao.BaseDao;
//import com.zy.modules.mallcoupon.entity.SeckillSkuNoticeEntity;
//import org.apache.ibatis.annotations.Mapper;

import com.zy.mallcoupon.entity.SeckillSkuNoticeEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀商品通知订阅
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface SeckillSkuNoticeDao extends BaseDao<SeckillSkuNoticeEntity> {
	
}