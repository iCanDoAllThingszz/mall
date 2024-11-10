package com.zy.mallware;

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
        WareInfoEntity wareInfoEntity = new WareInfoEntity();
        wareInfoEntity.setName("测试仓库");

        wareInfoService.save(wareInfoEntity);

        WareInfoEntity byId = wareInfoService.getById(1);
        System.out.println(byId);

        boolean b = wareInfoService.removeById(1);
        System.out.println(b);
    }

}
