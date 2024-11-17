package com.zy.mallorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallorder.dao.OrderItemDao;
import com.zy.mallorder.dto.OrderItemDTO;
import com.zy.mallorder.entity.OrderItemEntity;
import com.zy.mallorder.service.OrderItemService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class OrderItemServiceImpl extends CrudServiceImpl<OrderItemDao, OrderItemEntity, OrderItemDTO> implements OrderItemService {

    @Override
    public QueryWrapper<OrderItemEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}