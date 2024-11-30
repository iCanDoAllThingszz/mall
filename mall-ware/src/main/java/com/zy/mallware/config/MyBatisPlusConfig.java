package com.zy.mallware.config;/**
 * @Author:zhoayu
 * @Date:2024/11/24 15:55
 * @Description:com.zy.mallproduct.interceptor
 * @version:1.0
 */


import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisPlusConfig
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/24
 */

@Configuration
@MapperScan("com.zy.mallware.dao")
public class MyBatisPlusConfig {

    @Bean
   public PaginationInnerInterceptor paginationInnerInterceptor(){
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();

        //设置请求的页面超过最大页时, true返回首页
       paginationInnerInterceptor.setOverflow(true);

       //设置最大单页限制500条, -1不受限制
       paginationInnerInterceptor.setMaxLimit(500L);

       //开启left join时的count优化
       //paginationInnerInterceptor.setOptimizeJoin(true);

       return paginationInnerInterceptor;
   }

}

 