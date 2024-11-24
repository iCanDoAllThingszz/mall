package com.zy.mallproduct.service;

import io.renren.common.service.CrudService;
import com.zy.mallproduct.dto.CategoryBrandRelationDTO;
import com.zy.mallproduct.entity.CategoryBrandRelationEntity;

/**
 * 品牌分类关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface CategoryBrandRelationService extends CrudService<CategoryBrandRelationEntity, CategoryBrandRelationDTO> {

    void saveDetail(CategoryBrandRelationDTO dto);

    void updateBrandName(Long brandId, String name);

    void updateCategoryName(Long catId, String name);

}