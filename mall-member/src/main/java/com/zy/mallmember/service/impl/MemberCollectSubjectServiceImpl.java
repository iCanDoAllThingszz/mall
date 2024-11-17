package com.zy.mallmember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zy.mallmember.dao.MemberCollectSubjectDao;
import com.zy.mallmember.dto.MemberCollectSubjectDTO;
import com.zy.mallmember.entity.MemberCollectSubjectEntity;
import com.zy.mallmember.service.MemberCollectSubjectService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class MemberCollectSubjectServiceImpl extends CrudServiceImpl<MemberCollectSubjectDao, MemberCollectSubjectEntity, MemberCollectSubjectDTO> implements MemberCollectSubjectService {

    @Override
    public QueryWrapper<MemberCollectSubjectEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberCollectSubjectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}