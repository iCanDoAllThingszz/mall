package com.zy.mallproduct;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.mall.common.utils.PageUtils;
import com.zy.mallproduct.entity.BrandEntity;
import com.zy.mallproduct.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MallProductApplicationTests {
    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
    }

    @Test
    public void brandServiceTestSave(){
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("华为");
        brandEntity.setLogo("huawei.png");
        brandEntity.setDescript("huawei");
        brandEntity.setFirstLetter("H");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }

    @Test
    public void brandServiceTestSelect(){
        BrandEntity byId = brandService.getById(1);
        System.out.println(byId);

        PageUtils pageUtils = brandService.queryPage(new HashMap<>());
        List<?> list = pageUtils.getList();
        System.out.println(list);

        QueryWrapper<BrandEntity> brandEntityQueryWrapper = new QueryWrapper<>();
        brandEntityQueryWrapper.like("name", "华");

        Page<BrandEntity> page = brandService.page(new Page<>(1, 2), brandEntityQueryWrapper);
        System.out.println(page.getRecords());
    }

}
