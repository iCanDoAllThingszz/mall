package com.zy.mallcoupon;

//import com.zy.mallcoupon.entity.CouponEntity;
//import com.zy.mallcoupon.service.CouponService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

import com.zy.mallcoupon.dto.CouponDTO;
import com.zy.mallcoupon.entity.CouponEntity;
import com.zy.mallcoupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
class MallCouponApplicationTests {
    @Autowired
    private CouponService couponService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponName("100元优惠券");
        couponDTO.setCouponType(0);
        couponDTO.setCouponImg("100.png");



        couponService.save(couponDTO);
        CouponDTO byId = couponService.get(1L);
        System.out.println(byId);

        boolean b = couponService.deleteById(1);
        System.out.println(b);
    }

}
