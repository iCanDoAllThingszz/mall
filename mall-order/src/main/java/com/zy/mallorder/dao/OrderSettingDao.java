package com.zy.mallorder.dao;


import com.zy.mallorder.entity.OrderSettingEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface OrderSettingDao extends BaseDao<OrderSettingEntity> {
	
}