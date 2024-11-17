package com.zy.mallware.dao;

import com.zy.mallware.entity.PurchaseEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface PurchaseDao extends BaseDao<PurchaseEntity> {
	
}