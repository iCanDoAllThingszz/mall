package com.zy.mallproduct.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zy.mallproduct.dao.CategoryBrandRelationDao;
import com.zy.mallproduct.dto.BrandDTO;
import com.zy.mallproduct.dto.CategoryBrandRelationDTO;
import com.zy.mallproduct.dto.CategoryDTO;
import com.zy.mallproduct.entity.CategoryBrandRelationEntity;
import com.zy.mallproduct.service.BrandService;
import com.zy.mallproduct.service.CategoryBrandRelationService;
import com.zy.mallproduct.service.CategoryService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class CategoryBrandRelationServiceImpl extends CrudServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity, CategoryBrandRelationDTO> implements CategoryBrandRelationService {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public QueryWrapper<CategoryBrandRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        Long catId = (Long)params.get("catId");

        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(catId!=null, "catelog_id", catId);

        return wrapper;
    }


    @Override
    public void saveDetail(CategoryBrandRelationDTO dto) {
        // 根据商品类别id和品牌id查询出对应的类别名称和品牌名称
        Long brandId = dto.getBrandId();
        Long catelogId = dto.getCatelogId();

        CategoryDTO categoryDTO = categoryService.get(catelogId);

        BrandDTO brandDTO = brandService.get(brandId);

        dto.setBrandName(brandDTO.getName());
        dto.setCatelogName(categoryDTO.getName());

        this.save(dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBrandName(Long brandId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(brandId);
        categoryBrandRelationEntity.setBrandName(name);

        UpdateWrapper<CategoryBrandRelationEntity> categoryBrandRelationEntityUpdateWrapper = new UpdateWrapper<>();
        categoryBrandRelationEntityUpdateWrapper.eq("brand_id", brandId);
        categoryBrandRelationEntityUpdateWrapper.set("brand_name", name);

        this.update(categoryBrandRelationEntity, categoryBrandRelationEntityUpdateWrapper);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCategoryName(Long catId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setCatelogId(catId);
        categoryBrandRelationEntity.setCatelogName(name);

        UpdateWrapper<CategoryBrandRelationEntity> categoryBrandRelationEntityUpdateWrapper = new UpdateWrapper<>();
        categoryBrandRelationEntityUpdateWrapper.eq("catelog_id", catId);
        categoryBrandRelationEntityUpdateWrapper.set("catelog_name", name);

        this.update(categoryBrandRelationEntity, categoryBrandRelationEntityUpdateWrapper);
    }
}