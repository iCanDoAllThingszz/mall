package com.zy.mallware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallware.dao.PurchaseDao;
import com.zy.mallware.dto.PurchaseDTO;
import com.zy.mallware.dto.WareOrderTaskDTO;
import com.zy.mallware.entity.PurchaseEntity;
import com.zy.mallware.service.PurchaseService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class PurchaseServiceImpl extends CrudServiceImpl<PurchaseDao, PurchaseEntity, PurchaseDTO> implements PurchaseService {

    @Override
    public QueryWrapper<PurchaseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<PurchaseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public WareOrderTaskDTO mergeOrAddWareTaskOrder(List<PurchaseDTO> purchaseDTOS, Boolean merge, String wareTaskOrderId) {
        //1. 判断是新建采购单还是合并采购需求到采购单
        if (merge) {
            //获得各采购需求的信息, 并合并



        } else {

        }

        return null;
    }
}