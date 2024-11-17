package com.zy.mallmember.dao;


import com.zy.mallmember.entity.MemberEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {
	
}