package com.zy.mallcoupon.dao;

//import com.zy.mallcoupon.dao.BaseDao;
//import com.zy.modules.mallcoupon.entity.HomeSubjectSpuEntity;
//import org.apache.ibatis.annotations.Mapper;

import com.zy.mallcoupon.entity.HomeSubjectSpuEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专题商品
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface HomeSubjectSpuDao extends BaseDao<HomeSubjectSpuEntity> {
	
}