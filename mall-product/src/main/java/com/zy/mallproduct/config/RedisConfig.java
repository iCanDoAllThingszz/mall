package com.zy.mallproduct.config;/**
 * @Author:zhoayu
 * @Date:2024/12/9 22:50
 * @Description:com.zy.mallproduct.config
 * @version:1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName RedisConfig
 * @Description //Redis实例bean
 * @Author zhaoyu
 * @Date 2024/12/9
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 配置 Redis 连接工厂，选择数据库 1
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setDatabase(0);
        factory.setHostName(host);
        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
}

 