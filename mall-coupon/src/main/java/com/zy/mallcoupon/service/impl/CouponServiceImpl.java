package com.zy.mallcoupon.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.mallcoupon.service.impl.CrudServiceImpl;
//import com.zy.modules.mallcoupon.dao.CouponDao;
//import com.zy.modules.mallcoupon.dto.CouponDTO;
//import com.zy.modules.mallcoupon.entity.CouponEntity;
//import com.zy.modules.mallcoupon.service.CouponService;
//import cn.hutool.core.util.StrUtil;
//import org.springframework.stereotype.Service;

//import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallcoupon.dao.CouponDao;
import com.zy.mallcoupon.dto.CouponDTO;
import com.zy.mallcoupon.entity.CouponEntity;
import com.zy.mallcoupon.service.CouponService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 优惠券信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class CouponServiceImpl extends CrudServiceImpl<CouponDao, CouponEntity, CouponDTO> implements CouponService {

    @Override
    public QueryWrapper<CouponEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CouponEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}