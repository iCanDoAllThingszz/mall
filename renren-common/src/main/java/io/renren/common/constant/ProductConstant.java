package io.renren.common.constant;

import lombok.Getter;

/**
 * @Author:zhoayu
 * @Date:2024/11/27 12:56
 * @Description:io.renren.common.constant
 * @version:1.0
 */
public interface ProductConstant {

//    /**
//     * 商品属性: 基本属性
//     * */
//    Integer ATTR_TYPE_BASE = 1;
//
//    /**
//     * 商品属性: 销售属性
//     * */
//    Integer ATTR_TYPE_SELL = 0;

    @Getter
    enum AttrTypeEnum {
        ATTR_TYPE_BASE(1, "基本属性"),
        ATTR_TYPE_SELL(0, "销售属性");

        private final Integer code;
        private final String message;

        AttrTypeEnum(Integer code, String message){
            this.code = code;
            this.message = message;
        }

    }

}
