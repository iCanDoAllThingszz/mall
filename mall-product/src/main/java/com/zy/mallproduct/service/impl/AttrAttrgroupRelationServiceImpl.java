package com.zy.mallproduct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import com.zy.mallproduct.dao.AttrAttrgroupRelationDao;
import com.zy.mallproduct.dto.AttrAttrgroupRelationDTO;
import com.zy.mallproduct.entity.AttrAttrgroupRelationEntity;
import com.zy.mallproduct.service.AttrAttrgroupRelationService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class AttrAttrgroupRelationServiceImpl extends CrudServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity, AttrAttrgroupRelationDTO> implements AttrAttrgroupRelationService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public QueryWrapper<AttrAttrgroupRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String attrId = (String)params.get("attrId");

        QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(StrUtil.isNotBlank(attrId), "attr_id", attrId);

        return wrapper;
    }


    @Override
    public AttrAttrgroupRelationDTO query(Long attrId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("attrId", String.valueOf(attrId));

        return ConvertUtils.sourceToTarget(attrAttrgroupRelationDao.selectOne(this.getWrapper(queryParams)), AttrAttrgroupRelationDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByAttrId(Long attrId) {
        UpdateWrapper<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntityUpdateWrapper = new UpdateWrapper<>();
        attrAttrgroupRelationEntityUpdateWrapper.eq("attr_id", attrId);

        attrAttrgroupRelationDao.delete(attrAttrgroupRelationEntityUpdateWrapper);
    }
}