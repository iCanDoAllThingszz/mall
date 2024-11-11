package com.zy.mallproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.mall.common.utils.PageUtils;
import com.zy.mallproduct.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 00:31:28
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
