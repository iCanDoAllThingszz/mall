package com.zy.mallcoupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.mall.common.utils.PageUtils;
import com.zy.mallcoupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 12:07:17
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

