package com.zy.mallproduct.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallproduct.dao.BrandDao;
import com.zy.mallproduct.dto.BrandDTO;
import com.zy.mallproduct.dto.CategoryBrandRelationDTO;
import com.zy.mallproduct.entity.BrandEntity;
import com.zy.mallproduct.service.BrandService;
import com.zy.mallproduct.service.CategoryBrandRelationService;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<BrandDao, BrandEntity, BrandDTO> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private BrandDao brandDao;

    @Override
    public QueryWrapper<BrandEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    /**
     * 更新brand表的同时, 更新brand-category关系表中的brand_name
     * */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCascade(BrandDTO dto) {
        this.update(dto);

        //如果brand表中的brand_name被更新了, 就需要同步级联更新 brand-category 表中的brand_name; 并且必须注意事务
        if(StrUtil.isNotBlank(dto.getName())){
            Long brandId = dto.getBrandId();
            String name = dto.getName();

            categoryBrandRelationService.updateBrandName(brandId, name);

            //事务测试
            System.out.println(1/(dto.getBrandId()-9990));
            //todo: 同步更新其他可能有brand_name字段的表
        }
    }

    @Override
    public List<BrandDTO> findBrandByCatId(Long catId) {
        HashMap<String, Object> queryParam = new HashMap<>();
        queryParam.put("catId", catId);

        // 1.查询 三级分类-品牌 关联关系表, 根据三级分类id得到其对应的所有brandId
        List<CategoryBrandRelationDTO> list = categoryBrandRelationService.list(queryParam);

        // 2.查询所有的brandDTO
        return this.selectBatch(list);
    }

    @Override
    public List<BrandDTO> selectBatch(List<CategoryBrandRelationDTO> list) {
        QueryWrapper<BrandEntity> brandEntityQueryWrapper = new QueryWrapper<>();

        for (CategoryBrandRelationDTO categoryBrandRelationDTO : list) {
            if (categoryBrandRelationDTO.getBrandId() != null) {
                brandEntityQueryWrapper.or(wrapper -> wrapper.eq("brand_id", categoryBrandRelationDTO.getBrandId()));
            }
        }

        return ConvertUtils.sourceToTarget(brandDao.selectList(brandEntityQueryWrapper), BrandDTO.class);
    }
}