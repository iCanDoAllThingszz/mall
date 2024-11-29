package com.zy.mallproduct.dao;


import com.zy.mallproduct.entity.AttrAttrgroupRelationEntity;
import io.renren.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseDao<AttrAttrgroupRelationEntity> {

    void insertBatch(List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntity);

}