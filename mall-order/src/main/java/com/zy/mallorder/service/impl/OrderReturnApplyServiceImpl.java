package com.zy.mallorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallorder.dao.OrderReturnApplyDao;
import com.zy.mallorder.dto.OrderReturnApplyDTO;
import com.zy.mallorder.entity.OrderReturnApplyEntity;
import com.zy.mallorder.service.OrderReturnApplyService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class OrderReturnApplyServiceImpl extends CrudServiceImpl<OrderReturnApplyDao, OrderReturnApplyEntity, OrderReturnApplyDTO> implements OrderReturnApplyService {

    @Override
    public QueryWrapper<OrderReturnApplyEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderReturnApplyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}