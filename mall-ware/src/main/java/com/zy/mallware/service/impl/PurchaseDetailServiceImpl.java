package com.zy.mallware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallware.dao.PurchaseDetailDao;
import com.zy.mallware.dto.PurchaseDetailDTO;
import com.zy.mallware.entity.PurchaseDetailEntity;
import com.zy.mallware.service.PurchaseDetailService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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

        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}