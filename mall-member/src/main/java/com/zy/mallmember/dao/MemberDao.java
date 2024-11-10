package com.zy.mallmember.dao;

import com.zy.mallmember.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 12:16:52
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
