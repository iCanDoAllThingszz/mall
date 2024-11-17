package com.zy.mallmember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zy.mallmember.dao.MemberLoginLogDao;
import com.zy.mallmember.dto.MemberLoginLogDTO;
import com.zy.mallmember.entity.MemberLoginLogEntity;
import com.zy.mallmember.service.MemberLoginLogService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class MemberLoginLogServiceImpl extends CrudServiceImpl<MemberLoginLogDao, MemberLoginLogEntity, MemberLoginLogDTO> implements MemberLoginLogService {

    @Override
    public QueryWrapper<MemberLoginLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberLoginLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}