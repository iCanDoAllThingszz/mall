package com.zy.mallproduct.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallproduct.AttrVO;
import com.zy.mallproduct.dao.AttrDao;
import com.zy.mallproduct.dto.AttrAttrgroupRelationDTO;
import com.zy.mallproduct.dto.AttrDTO;
import com.zy.mallproduct.entity.AttrEntity;
import com.zy.mallproduct.service.AttrAttrgroupRelationService;
import com.zy.mallproduct.service.AttrService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 商品属性
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class AttrServiceImpl extends CrudServiceImpl<AttrDao, AttrEntity, AttrDTO> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Override
    public QueryWrapper<AttrEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttr(AttrVO attrvo) {
        // 1.保存规格参数信息
        AttrDTO attrDTO = new AttrDTO();
        BeanUtils.copyProperties(attrvo, attrDTO);
        this.save(attrDTO);

        // 2.保存规格参数 - 属性组映射关系
        if (attrvo.getAttrGroupId() != null) {
            AttrAttrgroupRelationDTO attrAttrgroupRelationDTO = new AttrAttrgroupRelationDTO();
            attrAttrgroupRelationDTO.setAttrId(attrDTO.getAttrId());
            attrAttrgroupRelationDTO.setAttrGroupId(attrvo.getAttrGroupId());
            attrAttrgroupRelationService.save(attrAttrgroupRelationDTO);

            System.out.println(1/(attrvo.getAttrGroupId()-9999));
        }


    }
}