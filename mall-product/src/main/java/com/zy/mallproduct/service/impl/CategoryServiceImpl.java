package com.zy.mallproduct.service.impl;

import com.zy.mall.common.utils.PageUtils;
import com.zy.mall.common.utils.R;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.mall.common.utils.Query;

import com.zy.mallproduct.dao.CategoryDao;
import com.zy.mallproduct.entity.CategoryEntity;
import com.zy.mallproduct.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> queryPageTree(Map<String, Object> params) {
        // 1.不带条件查询所有商品
        List<CategoryEntity> categoryEntities = categoryDao.selectList(null);

        // 2.1 遍历出所有的一级品类(parent_id=0)
        // 2.2 根据一级品类找到对应的所有小类
        // 通过stream流实现
        List<CategoryEntity> collect = categoryEntities.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(categoryEntity -> {
                    // 根据大类按照血缘关系递归找到子类
                    categoryEntity.setChildrens(getCategoryChildrens(categoryEntity, categoryEntities));

                    return categoryEntity;
                })
                // compareTo方法: 返回正数 则改变o1,o2顺序; 返回0或负数 不改变o1,o2顺序
                .sorted((o1, o2) -> o1.getSort() == null ? 0 : o1.getSort() - (o2.getSort() == null ? 0 : o2.getSort()))
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * @description 递归方法, 根据大类按照血缘关系递归找到子类
     * @param category 某个大类
     * @categoryEntities 所有类别数据
     * */
    private List<CategoryEntity> getCategoryChildrens(CategoryEntity category, List<CategoryEntity> categoryEntities) {
        if (category == null || CollectionUtils.isEmpty(categoryEntities)) {
            return new ArrayList<>();
        }

        // 根据大类找到直属小类
        List<CategoryEntity> collect = categoryEntities.stream().filter(categoryEntity -> categoryEntity.getParentCid().equals(category.getCatId()))
                .map(categoryEntity -> {
                    categoryEntity.setChildrens(getCategoryChildrens(categoryEntity, categoryEntities));
                    return categoryEntity;
                })
                .sorted((o1, o2) -> o1.getSort() == null ? 0 : o1.getSort() - (o2.getSort() == null ? 0 : o2.getSort()))
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * @description 根据catIds删除类别信息
     * @param catIds 目录id主键
     * */
    public void removeCategoryByIds(List<Long> catIds) {
        // 1.检查类别数据是否在其他业务中使用
        // todo 其他服务就绪后补充此步骤

        // 2.逻辑删除 注意不要使用 this.removeCategoryByIds(catIds), 否则自己调自己, 事务失效
        categoryDao.deleteBatchIds(catIds);
    }

}