package com.zy.mallmember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zy.mallmember.dao.MemberStatisticsInfoDao;
import com.zy.mallmember.dto.MemberStatisticsInfoDTO;
import com.zy.mallmember.entity.MemberStatisticsInfoEntity;
import com.zy.mallmember.service.MemberStatisticsInfoService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class MemberStatisticsInfoServiceImpl extends CrudServiceImpl<MemberStatisticsInfoDao, MemberStatisticsInfoEntity, MemberStatisticsInfoDTO> implements MemberStatisticsInfoService {

    @Override
    public QueryWrapper<MemberStatisticsInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberStatisticsInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}