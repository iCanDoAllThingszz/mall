package com.zy.mallcoupon.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.mallcoupon.service.impl.CrudServiceImpl;
//import com.zy.modules.mallcoupon.dao.SkuFullReductionDao;
//import com.zy.modules.mallcoupon.dto.SkuFullReductionDTO;
//import com.zy.modules.mallcoupon.entity.SkuFullReductionEntity;
//import com.zy.modules.mallcoupon.service.SkuFullReductionService;
//import cn.hutool.core.util.StrUtil;
//import org.springframework.stereotype.Service;

//import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallcoupon.dao.SkuFullReductionDao;
import com.zy.mallcoupon.dto.SkuFullReductionDTO;
import com.zy.mallcoupon.entity.SkuFullReductionEntity;
import com.zy.mallcoupon.service.SkuFullReductionService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class SkuFullReductionServiceImpl extends CrudServiceImpl<SkuFullReductionDao, SkuFullReductionEntity, SkuFullReductionDTO> implements SkuFullReductionService {

    @Override
    public QueryWrapper<SkuFullReductionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SkuFullReductionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}