package com.zy.mallware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zy.common.service.impl.CrudServiceImpl;
import com.zy.mallware.dao.WareInfoDao;
import com.zy.mallware.dto.WareInfoDTO;
import com.zy.mallware.entity.WareInfoEntity;
import com.zy.mallware.service.WareInfoService;
import cn.hutool.core.util.StrUtil;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class WareInfoServiceImpl extends CrudServiceImpl<WareInfoDao, WareInfoEntity, WareInfoDTO> implements WareInfoService {

    @Override
    public QueryWrapper<WareInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String wareName = (String)params.get("wareName");
        String wareAddress = (String)params.get("wareAddress");
        String wareAreaCode = (String)params.get("wareAreaCode");

        QueryWrapper<WareInfoEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.like(StrUtil.isNotBlank(wareName), "name", wareName);
        wrapper.like(StrUtil.isNotBlank(wareAddress), "address", wareAddress);
        wrapper.like(StrUtil.isNotBlank(wareAreaCode), "areacode", wareAreaCode);

        return wrapper;
    }


}