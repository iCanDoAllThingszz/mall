package com.zy.mallmember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zy.mallmember.dao.GrowthChangeHistoryDao;
import com.zy.mallmember.dto.GrowthChangeHistoryDTO;
import com.zy.mallmember.entity.GrowthChangeHistoryEntity;
import com.zy.mallmember.service.GrowthChangeHistoryService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class GrowthChangeHistoryServiceImpl extends CrudServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistoryEntity, GrowthChangeHistoryDTO> implements GrowthChangeHistoryService {

    @Override
    public QueryWrapper<GrowthChangeHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GrowthChangeHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}