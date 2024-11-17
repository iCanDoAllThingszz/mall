package com.zy.mallcoupon.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.mallcoupon.service.impl.CrudServiceImpl;
//import com.zy.modules.mallcoupon.dao.MemberPriceDao;
//import com.zy.modules.mallcoupon.dto.MemberPriceDTO;
//import com.zy.modules.mallcoupon.entity.MemberPriceEntity;
//import com.zy.modules.mallcoupon.service.MemberPriceService;
//import cn.hutool.core.util.StrUtil;
//import org.springframework.stereotype.Service;

//import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallcoupon.dao.MemberPriceDao;
import com.zy.mallcoupon.dto.MemberPriceDTO;
import com.zy.mallcoupon.entity.MemberPriceEntity;
import com.zy.mallcoupon.service.MemberPriceService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class MemberPriceServiceImpl extends CrudServiceImpl<MemberPriceDao, MemberPriceEntity, MemberPriceDTO> implements MemberPriceService {

    @Override
    public QueryWrapper<MemberPriceEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberPriceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}