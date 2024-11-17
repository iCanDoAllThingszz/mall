package com.zy.mallorder.service;

//import com.zy.common.service.CrudService;
import com.zy.mallorder.dto.OrderOperateHistoryDTO;
import com.zy.mallorder.entity.OrderOperateHistoryEntity;
import io.renren.common.service.CrudService;

/**
 * 订单操作历史记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface OrderOperateHistoryService extends CrudService<OrderOperateHistoryEntity, OrderOperateHistoryDTO> {

}