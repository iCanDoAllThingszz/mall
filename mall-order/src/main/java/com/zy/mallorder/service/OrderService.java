package com.zy.mallorder.service;

//import com.zy.common.service.CrudService;
import com.zy.mallorder.dto.OrderDTO;
import com.zy.mallorder.entity.OrderEntity;
import io.renren.common.service.CrudService;

/**
 * 订单
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface OrderService extends CrudService<OrderEntity, OrderDTO> {

}