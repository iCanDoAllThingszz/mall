package com.zy.mallcoupon.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.mallcoupon.service.impl.CrudServiceImpl;
//import com.zy.modules.mallcoupon.dao.HomeAdvDao;
//import com.zy.modules.mallcoupon.dto.HomeAdvDTO;
//import com.zy.modules.mallcoupon.entity.HomeAdvEntity;
//import com.zy.modules.mallcoupon.service.HomeAdvService;
//import cn.hutool.core.util.StrUtil;
//import org.springframework.stereotype.Service;

//import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallcoupon.dao.HomeAdvDao;
import com.zy.mallcoupon.dto.HomeAdvDTO;
import com.zy.mallcoupon.entity.HomeAdvEntity;
import com.zy.mallcoupon.service.HomeAdvService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class HomeAdvServiceImpl extends CrudServiceImpl<HomeAdvDao, HomeAdvEntity, HomeAdvDTO> implements HomeAdvService {

    @Override
    public QueryWrapper<HomeAdvEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<HomeAdvEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}