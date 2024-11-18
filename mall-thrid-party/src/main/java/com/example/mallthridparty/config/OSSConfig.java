package com.example.mallthridparty.config;/**
 * @Author:zhoayu
 * @Date:2024/11/18 22:56
 * @Description:com.example.mallthridparty.config
 * @version:1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName OSSConfig
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/18
 */

@Configuration
//@ConfigurationProperties 注解是 Spring Boot 提供的一个功能，它允许你将外部配置（如 application.properties 或 application.yml 文件中的配置）绑定到一个 POJO（Plain Old Java Object）上
//在 Java 中，字段名称通常是驼峰命名法（camelCase），而在配置文件中，属性名称通常是以点分隔的（如 end-point）。@ConfigurationProperties 注解会自动处理这种映射
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class OSSConfig {

    private String endPoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

}
