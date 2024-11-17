package com.zy.mallorder.dao;


import com.zy.mallorder.entity.OrderReturnApplyEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单退货申请
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface OrderReturnApplyDao extends BaseDao<OrderReturnApplyEntity> {
	
}