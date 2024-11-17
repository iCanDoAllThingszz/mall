package com.zy.mallcoupon.service;

//import com.zy.mallcoupon.service.CrudService;
//import com.zy.modules.mallcoupon.dto.HomeSubjectDTO;
//import com.zy.modules.mallcoupon.entity.HomeSubjectEntity;

import com.zy.mallcoupon.dto.HomeSubjectDTO;
import com.zy.mallcoupon.entity.HomeSubjectEntity;
import io.renren.common.service.CrudService;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface HomeSubjectService extends CrudService<HomeSubjectEntity, HomeSubjectDTO> {

}