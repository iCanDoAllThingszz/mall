package com.zy.mallorder.dao;


import com.zy.mallorder.entity.OrderReturnReasonEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface OrderReturnReasonDao extends BaseDao<OrderReturnReasonEntity> {
	
}