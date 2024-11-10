package com.zy.mallorder.feign;/**
 * @Author:zhoayu
 * @Date:2024/11/10 18:16
 * @Description:com.zy.mallorder.feign
 * @version:1.0
 */

import com.zy.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName ProductOpenFeignService
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/10
 */

@FeignClient(name="mall-product") // 说明要调用的服务名称
public interface ProductOpenFeignService {

    //需要访问的远程方法
    @GetMapping("mallproduct/brand/all")
    public R queryAllBrand();
}
