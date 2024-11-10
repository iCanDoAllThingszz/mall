package com.zy.mallware.dao;

import com.zy.mallware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 12:24:14
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
