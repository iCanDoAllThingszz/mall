package com.zy.mallorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallorder.dao.OrderReturnReasonDao;
import com.zy.mallorder.dto.OrderReturnReasonDTO;
import com.zy.mallorder.entity.OrderReturnReasonEntity;
import com.zy.mallorder.service.OrderReturnReasonService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 退货原因
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class OrderReturnReasonServiceImpl extends CrudServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity, OrderReturnReasonDTO> implements OrderReturnReasonService {

    @Override
    public QueryWrapper<OrderReturnReasonEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderReturnReasonEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}