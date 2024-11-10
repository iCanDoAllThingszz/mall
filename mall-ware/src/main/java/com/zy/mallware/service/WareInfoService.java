package com.zy.mallware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.mall.common.utils.PageUtils;
import com.zy.mallware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 12:24:14
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

