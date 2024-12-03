package com.zy.mallware.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallware.dao.WareOrderTaskDao;
import com.zy.mallware.dto.WareOrderTaskDTO;
import com.zy.mallware.entity.WareOrderTaskEntity;
import com.zy.mallware.service.WareOrderTaskService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * 库存工作单
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class WareOrderTaskServiceImpl extends CrudServiceImpl<WareOrderTaskDao, WareOrderTaskEntity, WareOrderTaskDTO> implements WareOrderTaskService {

    @Override
    public QueryWrapper<WareOrderTaskEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        ArrayList<Integer> taskStatus = (ArrayList<Integer>)params.get("taskStatus");

        QueryWrapper<WareOrderTaskEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        for (Integer status : taskStatus) {
            wrapper.eq(status!=null, "status", status);
        }

        return wrapper;
    }


}