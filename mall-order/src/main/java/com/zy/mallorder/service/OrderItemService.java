package com.zy.mallorder.service;


import com.zy.mallorder.dto.OrderItemDTO;
import com.zy.mallorder.entity.OrderItemEntity;
import io.renren.common.service.CrudService;

/**
 * 订单项信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface OrderItemService extends CrudService<OrderItemEntity, OrderItemDTO> {

}