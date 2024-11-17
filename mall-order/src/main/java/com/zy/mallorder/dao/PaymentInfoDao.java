package com.zy.mallorder.dao;


import com.zy.mallorder.entity.PaymentInfoEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface PaymentInfoDao extends BaseDao<PaymentInfoEntity> {
	
}