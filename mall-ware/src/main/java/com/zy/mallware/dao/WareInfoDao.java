package com.zy.mallware.dao;

//import com.zy.common.dao.BaseDao;
import com.zy.mallware.entity.WareInfoEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface WareInfoDao extends BaseDao<WareInfoEntity> {
	
}