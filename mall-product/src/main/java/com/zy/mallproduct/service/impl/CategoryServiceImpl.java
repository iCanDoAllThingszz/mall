package com.zy.mallproduct.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.mallproduct.dao.CategoryDao;
import com.zy.mallproduct.dto.CategoryDTO;
import com.zy.mallproduct.entity.CategoryEntity;
import com.zy.mallproduct.service.CategoryBrandRelationService;
import com.zy.mallproduct.service.CategoryService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Service
public class CategoryServiceImpl extends CrudServiceImpl<CategoryDao, CategoryEntity, CategoryDTO> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public QueryWrapper<CategoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<CategoryEntity> queryPageTree(Map<String, Object> params) {
        List<CategoryEntity> collect = null;

        // 0.尝试从缓存中获取三级类目信息
        String categoryJson = stringRedisTemplate.opsForValue().get("categoryJson");
        if (StrUtil.isNotEmpty(categoryJson)) {
            try {
                JSONArray categoryJsonArray = JSONUtil.parseArray(categoryJson);
                System.out.println("三级类目json字符串从缓存中成功获取!");
                return categoryJsonArray.toList(CategoryEntity.class);
            } catch (Exception e) {
                System.out.println("缓存中获得的三级类目json字符串解析失败: " + e.getMessage());
            }
        }

        // 防止缓存击穿: 假设这个三级类目信息是一个热点key, 扛着大量并发请求; 现在突然过期了, 为了避免大量并发请求一起请求数据库 这里加锁处理
        // 这里锁this, 还是锁类的class对象, 在springboot中 默认创建的bean是单例, 所以这里锁this是可以的;
        // 其实这里在方法上加synchronized关键字也可以, 但是我们控制一下锁的粒度, 还是自己声明在方法里了
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " 成功获取锁!!");

            // 1.不带条件查询所有商品
            List<CategoryEntity> categoryEntities = categoryDao.selectList(null);

            // 防止缓存穿透: 如果数据库中无对应条件的三级类目信息, 在缓存中存一个空值
            // 防止缓存雪崩: 给key设置一个随机过期时间
            if (categoryEntities == null) {
                stringRedisTemplate.opsForValue().set("categoryJson", "[]", new Random().nextInt(5), TimeUnit.MINUTES);
                return new ArrayList<>();
            }

            // 2.1 遍历出所有的一级品类(parent_id=0)
            // 2.2 根据一级品类找到对应的所有小类
            // 通过stream流实现
            collect = categoryEntities.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                    .map(categoryEntity -> {
                        // 根据大类按照血缘关系递归找到子类
                        categoryEntity.setChildrens(getCategoryChildrens(categoryEntity, categoryEntities));

                        return categoryEntity;
                    })
                    // compareTo方法: 返回正数 则改变o1,o2顺序; 返回0或负数 不改变o1,o2顺序
                    .sorted((o1, o2) -> o1.getSort() == null ? 0 : o1.getSort() - (o2.getSort() == null ? 0 : o2.getSort()))
                    .collect(Collectors.toList());

            // 3.写入缓存
            String jsonStr = JSONUtil.toJsonStr(collect);
            stringRedisTemplate.opsForValue().set("categoryJson", jsonStr);
            System.out.println("三级类目json字符串写入缓存成功!");
        }

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCascade(CategoryDTO category) {
        this.update(category);

        //如果category表中的catelog_name被更新了, 就需要同步级联更新 brand-category 表中的catelog_name; 并且必须注意事务
        if(StrUtil.isNotBlank(category.getName())){
            Long catId = category.getCatId();
            String name = category.getName();

            categoryBrandRelationService.updateCategoryName(catId, name);

            //事务测试
            System.out.println(1/(category.getCatId()-9990));
            //todo: 同步更新其他可能有catelog_name字段的表
        }
    }


}