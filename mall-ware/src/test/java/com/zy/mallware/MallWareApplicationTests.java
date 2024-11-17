package com.zy.mallware;

import com.zy.mallware.dto.WareInfoDTO;
import com.zy.mallware.entity.WareInfoEntity;
import com.zy.mallware.service.WareInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallWareApplicationTests {

    @Autowired
    private WareInfoService wareInfoService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        WareInfoDTO wareInfoEntity = new WareInfoDTO();
        wareInfoEntity.setName("测试仓库");

        wareInfoService.save(wareInfoEntity);

        WareInfoDTO byId = wareInfoService.get(1L);
        System.out.println(byId);
    }

}
