package com.zy.mallorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallorder.dao.PaymentInfoDao;
import com.zy.mallorder.dto.PaymentInfoDTO;
import com.zy.mallorder.entity.PaymentInfoEntity;
import com.zy.mallorder.service.PaymentInfoService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class PaymentInfoServiceImpl extends CrudServiceImpl<PaymentInfoDao, PaymentInfoEntity, PaymentInfoDTO> implements PaymentInfoService {

    @Override
    public QueryWrapper<PaymentInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<PaymentInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}