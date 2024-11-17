package com.zy.mallproduct.dao;


import com.zy.mallproduct.entity.CommentReplayEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface CommentReplayDao extends BaseDao<CommentReplayEntity> {
	
}