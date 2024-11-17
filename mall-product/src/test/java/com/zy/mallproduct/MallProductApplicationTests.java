package com.zy.mallproduct;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.mallproduct.dto.BrandDTO;
import com.zy.mallproduct.entity.BrandEntity;
import com.zy.mallproduct.service.BrandService;
import io.renren.common.page.PageData;
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
    public void brandServiceTestSelect(){
        BrandDTO brandDTO = brandService.get(1L);
        System.out.println(brandDTO);

        PageData<BrandDTO> page1 = brandService.page(new HashMap<>());
        System.out.println(page1);

    }

}
