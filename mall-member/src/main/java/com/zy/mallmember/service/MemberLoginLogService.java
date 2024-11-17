package com.zy.mallmember.service;


import com.zy.mallmember.dto.MemberLoginLogDTO;
import com.zy.mallmember.entity.MemberLoginLogEntity;
import io.renren.common.service.CrudService;

/**
 * 会员登录记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface MemberLoginLogService extends CrudService<MemberLoginLogEntity, MemberLoginLogDTO> {

}