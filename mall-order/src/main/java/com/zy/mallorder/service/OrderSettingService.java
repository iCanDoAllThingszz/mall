package com.zy.mallorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.mall.common.utils.PageUtils;
import com.zy.mallorder.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 11:51:04
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

