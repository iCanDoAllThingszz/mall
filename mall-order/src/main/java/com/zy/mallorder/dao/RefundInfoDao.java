package com.zy.mallorder.dao;


import com.zy.mallorder.entity.RefundInfoEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface RefundInfoDao extends BaseDao<RefundInfoEntity> {
	
}