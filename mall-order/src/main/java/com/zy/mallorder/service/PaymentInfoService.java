package com.zy.mallorder.service;

//import com.zy.common.service.CrudService;
import com.zy.mallorder.dto.PaymentInfoDTO;
import com.zy.mallorder.entity.PaymentInfoEntity;
import io.renren.common.service.CrudService;

/**
 * 支付信息表
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface PaymentInfoService extends CrudService<PaymentInfoEntity, PaymentInfoDTO> {

}