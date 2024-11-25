package com.zy.mallproduct.service;

import com.zy.mallproduct.AttrVO;
import io.renren.common.service.CrudService;
import com.zy.mallproduct.dto.AttrDTO;
import com.zy.mallproduct.entity.AttrEntity;

/**
 * 商品属性
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface AttrService extends CrudService<AttrEntity, AttrDTO> {

    void saveAttr(AttrVO attrvo);

}