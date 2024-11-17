package com.zy.mallproduct.dao;


import com.zy.mallproduct.entity.SpuInfoEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface SpuInfoDao extends BaseDao<SpuInfoEntity> {
	
}