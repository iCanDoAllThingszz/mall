package com.zy.mallcoupon;

import com.zy.mallcoupon.entity.CouponEntity;
import com.zy.mallcoupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallCouponApplicationTests {
    @Autowired
    private CouponService couponService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("100元优惠券");
        couponEntity.setCouponType(0);
        couponEntity.setCouponImg("100.png");

        couponService.save(couponEntity);
        CouponEntity byId = couponService.getById(1);
        System.out.println(byId);

        boolean b = couponService.removeById(1);
        System.out.println(b);
    }

}
