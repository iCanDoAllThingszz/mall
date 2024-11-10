package com.zy.mallcoupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.mall.common.utils.PageUtils;
import com.zy.mallcoupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 12:07:17
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

