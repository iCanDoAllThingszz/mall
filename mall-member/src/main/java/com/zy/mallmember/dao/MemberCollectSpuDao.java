package com.zy.mallmember.dao;


import com.zy.mallmember.entity.MemberCollectSpuEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收藏的商品
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface MemberCollectSpuDao extends BaseDao<MemberCollectSpuEntity> {
	
}