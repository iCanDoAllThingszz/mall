package com.zy.mallproduct.service;

import com.zy.mallproduct.dto.AttrAttrgroupRelationDTO;
import com.zy.mallproduct.vo.AttrVO;
import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import com.zy.mallproduct.dto.AttrDTO;
import com.zy.mallproduct.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface AttrService extends CrudService<AttrEntity, AttrDTO> {

    void saveAttr(AttrVO attrvo);

    AttrDTO infoSupply(AttrDTO attrDTO);

    void updateCascade(AttrDTO dto);

    void deleteCascade(Long[] ids);

    List<AttrDTO> attrGroup(Long attrGroupId);

    void removeAttrGroupRel(AttrAttrgroupRelationDTO[] attrAttrgroupRelationDTO);

    PageData<AttrDTO> pageNoRelationAttr(Map<String, Object> params);


}