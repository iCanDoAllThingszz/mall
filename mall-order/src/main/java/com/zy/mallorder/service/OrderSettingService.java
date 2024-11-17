package com.zy.mallorder.service;

//import com.zy.common.service.CrudService;
import com.zy.mallorder.dto.OrderSettingDTO;
import com.zy.mallorder.entity.OrderSettingEntity;
import io.renren.common.service.CrudService;

/**
 * 订单配置信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface OrderSettingService extends CrudService<OrderSettingEntity, OrderSettingDTO> {

}