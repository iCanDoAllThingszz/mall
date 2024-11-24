package com.zy.mallproduct.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallproduct.dao.BrandDao;
import com.zy.mallproduct.dto.BrandDTO;
import com.zy.mallproduct.entity.BrandEntity;
import com.zy.mallproduct.service.BrandService;
import com.zy.mallproduct.service.CategoryBrandRelationService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}