package com.zy.mallproduct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import com.zy.mallproduct.dao.AttrGroupDao;
import com.zy.mallproduct.dto.AttrGroupDTO;
import com.zy.mallproduct.entity.AttrGroupEntity;
import com.zy.mallproduct.service.AttrGroupService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 属性分组
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class AttrGroupServiceImpl extends CrudServiceImpl<AttrGroupDao, AttrGroupEntity, AttrGroupDTO> implements AttrGroupService {

    @Override
    public QueryWrapper<AttrGroupEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String catelogId = (String)params.get("catelogId");
        String key = (String)params.get("key");

        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(StrUtil.isNotBlank(catelogId) && !catelogId.equals("0"), "catelog_id", catelogId);
        wrapper.and(StrUtil.isNotBlank(key) , obj -> obj.like("attr_group_name", key).or().like("descript", key));

        return wrapper;
    }


}