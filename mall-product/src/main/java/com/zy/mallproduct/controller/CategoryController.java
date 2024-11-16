package com.zy.mallproduct.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zy.mall.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zy.mallproduct.entity.CategoryEntity;
import com.zy.mallproduct.service.CategoryService;
import com.zy.mall.common.utils.R;



/**
 * 商品三级分类
 *
 * @author zhaoyu93
 * @email zhaoyu93@meituan.com
 * @date 2024-11-10 00:31:28
 */
@RestController
@RequestMapping("mallproduct/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mallproduct:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询所有的类别数据, 然后将数据封装为树形结构返回, 便于前端使用
     */
    @RequestMapping("/listTree")
    //@RequiresPermissions("mallproduct:category:list")
    public R listTree(@RequestParam Map<String, Object> params){
        // 1. 查询所有的商品分类信息
        // 2. 利用商品分类的父子关系 构造树形结构
        List<CategoryEntity> categoryEntities = categoryService.queryPageTree(params);

        return R.ok().put("categories", categoryEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("mallproduct:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mallproduct:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mallproduct:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 批次修改
     */
    @RequestMapping("/updateBatch")
    //@RequiresPermissions("mallproduct:category:update")
    public R updateBatch(@RequestBody CategoryEntity[] categories){
        categoryService.updateBatchById(Arrays.asList(categories));

        return R.ok();
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mallproduct:category:delete")
    public R delete(@RequestBody Long[] catIds){
		//categoryService.removeByIds(Arrays.asList(catIds));

        categoryService.removeCategoryByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
