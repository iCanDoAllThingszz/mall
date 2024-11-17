package com.zy.mallware.dao;

//import com.zy.common.dao.BaseDao;
import com.zy.mallware.entity.WareOrderTaskDetailEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存工作单
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface WareOrderTaskDetailDao extends BaseDao<WareOrderTaskDetailEntity> {
	
}