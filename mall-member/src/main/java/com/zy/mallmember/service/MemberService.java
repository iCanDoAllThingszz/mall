package com.zy.mallmember.service;


import com.zy.mallmember.dto.MemberDTO;
import com.zy.mallmember.entity.MemberEntity;
import io.renren.common.service.CrudService;

/**
 * 会员
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface MemberService extends CrudService<MemberEntity, MemberDTO> {

}