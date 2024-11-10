package com.zy.mallproduct.dao;

import com.zy.mallproduct.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 00:31:28
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
