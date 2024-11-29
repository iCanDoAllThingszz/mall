package com.zy.mallproduct.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.mallproduct.dao.AttrAttrgroupRelationDao;
import com.zy.mallproduct.dao.AttrDao;
import com.zy.mallproduct.dto.AttrAttrgroupRelationDTO;
import com.zy.mallproduct.dto.AttrDTO;
import com.zy.mallproduct.entity.AttrAttrgroupRelationEntity;
import com.zy.mallproduct.entity.AttrEntity;
import com.zy.mallproduct.entity.AttrGroupEntity;
import com.zy.mallproduct.service.AttrAttrgroupRelationService;
import com.zy.mallproduct.service.AttrGroupService;
import com.zy.mallproduct.service.AttrService;
import com.zy.mallproduct.service.CategoryService;
import com.zy.mallproduct.vo.AttrVO;
import io.renren.common.constant.ProductConstant;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public QueryWrapper<AttrEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String catelogId = (String)params.get("catelogId");
        String key = (String)params.get("key");
        String attrType = (String)params.get("attrType");

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        //queryWrapper中, 默认情况下多个条件是and连接的
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        //根据类别编号查询
        wrapper.eq(StrUtil.isNotBlank(catelogId), "catelog_id", catelogId);

        //根据key(关键字)模糊查询 规格参数id 或 规格参数名称
        wrapper.and( (w) -> {
            w.like("attr_name", key).or().like("attr_id", key);
        });

        wrapper.eq(StrUtil.isNotBlank(attrType), "attr_type", attrType);

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

    @Override
    public AttrDTO infoSupply(AttrDTO attrDTO) {
        // 补充规格参数对应的三级类目名称信息
        String catelogName = categoryService.selectById(attrDTO.getCatelogId()).getName();

        attrDTO.setCatelogName(catelogName);

        // 补充规格参数对应的属性分组名称, 通过关联表进行查询
        // 1.到关联表中得到规格参数对应的属性分组id
        AttrAttrgroupRelationDTO attrAttrgroupRelationDTO = attrAttrgroupRelationService.query(attrDTO.getAttrId());
        Long attrGroupId = attrAttrgroupRelationDTO.getAttrGroupId();

        // 销售属性无对应属性分组, 这就不用关联了
        if (attrGroupId != null && Objects.equals(attrDTO.getAttrType(), ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode())) {
            AttrGroupEntity attrGroupEntity = attrGroupService.selectById(attrGroupId);
            attrDTO.setAttrGroupName(attrGroupEntity.getAttrGroupName());
            attrDTO.setAttrGroupId(attrGroupId);
        }

        return attrDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCascade(AttrDTO dto) {
        Long attrId = dto.getAttrId();
        Long attrGroupId = dto.getAttrGroupId();

        this.update(dto);

        // 销售属性无对应属性分组, 这里就不用级联更新
        if (Objects.equals(dto.getAttrType(), ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode())) {
            AttrAttrgroupRelationEntity attrAttrgroupRelation = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelation.setAttrId(attrId);
            attrAttrgroupRelation.setAttrGroupId(attrGroupId);

            UpdateWrapper<AttrAttrgroupRelationEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(attrId!=null, "attr_id", attrId);
            attrAttrgroupRelationService.update(attrAttrgroupRelation, updateWrapper);
        }

        System.out.println(1/(attrGroupId-999));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCascade(Long[] ids) {

        for (Long id : ids) {
            AttrDTO attrDTO = this.get(id);
            // 1. 删除规格参数
            this.deleteById(id);

            if (attrDTO.getAttrType().equals(ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode())) {
                // 2. 删除 规格参数-属性分组 关系
                attrAttrgroupRelationService.deleteByAttrId(id);
            }
        }
    }

    @Override
    public List<AttrDTO> attrGroup(Long attrGroupId) {
        HashMap<String, Object> queryParam = new HashMap<>();
        queryParam.put("attrGroupId", attrGroupId);

        List<AttrAttrgroupRelationDTO> list = attrAttrgroupRelationService.list(queryParam);

        return list.stream().filter(x -> x.getAttrId()!=null).map(attrAttrgroupRelationDTO -> this.get(attrAttrgroupRelationDTO.getAttrId())).collect(Collectors.toList());
    }

    @Override
    public void removeAttrGroupRel(AttrAttrgroupRelationDTO[] attrAttrgroupRelationDTO) {
        UpdateWrapper<AttrAttrgroupRelationEntity> updateWrapper = new UpdateWrapper<>();

        for (AttrAttrgroupRelationDTO dto : attrAttrgroupRelationDTO) {
            updateWrapper.or(w -> w.eq(dto.getAttrId()!=null, "attr_id", dto.getAttrId()).eq(dto.getAttrGroupId()!=null, "attr_group_id", dto.getAttrGroupId()));
        }

        attrAttrgroupRelationService.remove(updateWrapper);
    }

    @Override
    public PageData<AttrDTO> pageNoRelationAttr(Map<String, Object> params) {
        // 查询当前attrGroupId对应的attrId
        String attrGroupId = (String)params.get("attrGroupId");
        QueryWrapper<AttrAttrgroupRelationEntity> queryWrapper = new QueryWrapper<AttrAttrgroupRelationEntity>().eq(attrGroupId != null, "attr_group_id", attrGroupId);

        List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities = attrAttrgroupRelationDao.selectList(queryWrapper);

        QueryWrapper<AttrEntity> queryWrapper2 = new QueryWrapper<>();

        for (AttrAttrgroupRelationEntity a : attrAttrgroupRelationEntities) {
            if(a.getAttrId()!=null){
                queryWrapper2.and(x -> x.notIn(a.getAttrId()!=null, "attr_id", a.getAttrId()));
            }
        }

        IPage<AttrEntity> attrEntityIPage = attrDao.selectPage(this.getPage(params, null, false), queryWrapper2);

        return getPageData(attrEntityIPage, currentDtoClass());
    }


}