package com.zy.mallproduct.dao;


import com.zy.mallproduct.entity.BrandEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface BrandDao extends BaseDao<BrandEntity> {
	
}