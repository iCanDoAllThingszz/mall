package com.zy.mallproduct.dao;


import com.zy.mallproduct.entity.AttrEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface AttrDao extends BaseDao<AttrEntity> {
	
}