package com.zy.mallproduct.service;

import com.zy.mallproduct.dto.CategoryBrandRelationDTO;
import io.renren.common.service.CrudService;
import com.zy.mallproduct.dto.BrandDTO;
import com.zy.mallproduct.entity.BrandEntity;

import java.util.List;

/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface BrandService extends CrudService<BrandEntity, BrandDTO> {

    void updateCascade(BrandDTO dto);

    List<BrandDTO> findBrandByCatId(Long catId);

    List<BrandDTO> selectBatch(List<CategoryBrandRelationDTO> list);

}