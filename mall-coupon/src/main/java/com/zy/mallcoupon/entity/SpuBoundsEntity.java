package com.zy.mallcoupon.entity;

//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Data;

//import java.math.BigDecimal;
//import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品spu积分设置
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@TableName("sms_spu_bounds")
public class SpuBoundsEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 
     */
	private Long spuId;
    /**
     * 成长积分
     */
	private BigDecimal growBounds;
    /**
     * 购物积分
     */
	private BigDecimal buyBounds;
    /**
     * 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
     */
	private Integer work;
}