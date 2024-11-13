package com.zy.config;/**
 * @Author:zhoayu
 * @Date:2024/11/13 18:55
 * @Description:com.zy.config
 * @version:1.0
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @ClassName MllCorsConfiguration
 * @Description // 解决跨域问题的过滤器
 * @Author zhaoyu
 * @Date 2024/11/13
 */

@Configuration
public class MllCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        // 基于url跨域
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 跨域配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        // springboot升级到2.4.0后需要使用addAllowedOriginPattern
        // corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOriginPattern("*");
        // 允许携带cookie
        corsConfiguration.setAllowCredentials(true);

        // 向source中注册new出来的配置，并设置任意url都要进行跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
