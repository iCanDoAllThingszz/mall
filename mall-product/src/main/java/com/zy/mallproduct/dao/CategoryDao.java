package com.zy.mallproduct.dao;


import com.zy.mallproduct.entity.CategoryEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {
	
}