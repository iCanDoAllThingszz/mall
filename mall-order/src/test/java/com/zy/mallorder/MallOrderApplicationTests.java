package com.zy.mallorder;

import com.zy.mallorder.dto.OrderDTO;
import com.zy.mallorder.entity.OrderEntity;
import com.zy.mallorder.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MallOrderApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        OrderDTO orderEntity = new OrderDTO();
        orderEntity.setOrderSn("123456789");
        orderEntity.setMemberId(1L);
        orderEntity.setCouponId(1L);
        orderService.save(orderEntity);
        OrderDTO byId = orderService.get(1L);
        System.out.println(byId);
    }

}
