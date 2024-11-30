package com.zy.mallware.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallware.dao.PurchaseDetailDao;
import com.zy.mallware.dto.PurchaseDetailDTO;
import com.zy.mallware.entity.PurchaseDetailEntity;
import com.zy.mallware.service.PurchaseDetailService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class PurchaseDetailServiceImpl extends CrudServiceImpl<PurchaseDetailDao, PurchaseDetailEntity, PurchaseDetailDTO> implements PurchaseDetailService {

    @Override
    public QueryWrapper<PurchaseDetailEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        Long purchaseId = (Long)params.get("purchaseId");
        Long skuId = (Long)params.get("skuId");
        Long wareId = (Long)params.get("wareId");
        String skuName = (String)params.get("skuName");

        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(Objects.nonNull(purchaseId), "purchase_id", purchaseId);
        wrapper.eq(Objects.nonNull(skuId), "sku_id", skuId);
        wrapper.eq(Objects.nonNull(wareId), "ware_id", wareId);
        wrapper.like(StrUtil.isNotBlank(skuName), "sku_name", skuName);

        return wrapper;
    }


}