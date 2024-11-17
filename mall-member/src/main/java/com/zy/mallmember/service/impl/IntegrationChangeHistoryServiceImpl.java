package com.zy.mallmember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zy.mallmember.dao.IntegrationChangeHistoryDao;
import com.zy.mallmember.dto.IntegrationChangeHistoryDTO;
import com.zy.mallmember.entity.IntegrationChangeHistoryEntity;
import com.zy.mallmember.service.IntegrationChangeHistoryService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class IntegrationChangeHistoryServiceImpl extends CrudServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity, IntegrationChangeHistoryDTO> implements IntegrationChangeHistoryService {

    @Override
    public QueryWrapper<IntegrationChangeHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<IntegrationChangeHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}