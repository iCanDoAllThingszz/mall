package com.zy.mallware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallware.dao.WareSkuDao;
import com.zy.mallware.dto.WareSkuDTO;
import com.zy.mallware.entity.WareSkuEntity;
import com.zy.mallware.service.WareSkuService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

/**
 * 商品库存
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class WareSkuServiceImpl extends CrudServiceImpl<WareSkuDao, WareSkuEntity, WareSkuDTO> implements WareSkuService {

    @Override
    public QueryWrapper<WareSkuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        Long skuId = (Long)params.get("skuId");
        Long wareId = (Long)params.get("wareId");
        String skuName = (String)params.get("skuName");

        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(skuId != null, "sku_id", skuId);
        wrapper.eq(wareId != null, "ware_id", wareId);
        wrapper.eq(StrUtil.isNotBlank(skuName), "sku_name", skuName);

        return wrapper;
    }


}