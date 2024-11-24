package com.zy.mallproduct.service;

import io.renren.common.service.CrudService;
import com.zy.mallproduct.dto.CategoryDTO;
import com.zy.mallproduct.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface CategoryService extends CrudService<CategoryEntity, CategoryDTO> {

    List<CategoryEntity> queryPageTree(Map<String, Object> params);

    void updateCascade(CategoryDTO category);

}