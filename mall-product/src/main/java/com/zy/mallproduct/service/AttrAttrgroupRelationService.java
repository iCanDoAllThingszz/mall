package com.zy.mallproduct.service;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zy.mallproduct.dto.AttrAttrgroupRelationDTO;
import com.zy.mallproduct.entity.AttrAttrgroupRelationEntity;
import io.renren.common.service.CrudService;

/**
 * 属性&属性分组关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
public interface AttrAttrgroupRelationService extends CrudService<AttrAttrgroupRelationEntity, AttrAttrgroupRelationDTO> {

    AttrAttrgroupRelationDTO query(Long attrId);

    void deleteByAttrId(Long id);

    void remove(UpdateWrapper<AttrAttrgroupRelationEntity> updateWrapper);

    void saveBatch(AttrAttrgroupRelationDTO[] dtos);

}