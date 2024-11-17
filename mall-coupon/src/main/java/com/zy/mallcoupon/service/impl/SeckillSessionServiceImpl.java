package com.zy.mallcoupon.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.mallcoupon.service.impl.CrudServiceImpl;
//import com.zy.modules.mallcoupon.dao.SeckillSessionDao;
//import com.zy.modules.mallcoupon.dto.SeckillSessionDTO;
//import com.zy.modules.mallcoupon.entity.SeckillSessionEntity;
//import com.zy.modules.mallcoupon.service.SeckillSessionService;
//import cn.hutool.core.util.StrUtil;
//import org.springframework.stereotype.Service;

//import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallcoupon.dao.SeckillSessionDao;
import com.zy.mallcoupon.dto.SeckillSessionDTO;
import com.zy.mallcoupon.entity.SeckillSessionEntity;
import com.zy.mallcoupon.service.SeckillSessionService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class SeckillSessionServiceImpl extends CrudServiceImpl<SeckillSessionDao, SeckillSessionEntity, SeckillSessionDTO> implements SeckillSessionService {

    @Override
    public QueryWrapper<SeckillSessionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SeckillSessionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}