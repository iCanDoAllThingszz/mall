package com.example.mallthridparty.config;/**
 * @Author:zhoayu
 * @Date:2024/11/19 12:44
 * @Description:com.example.mallthridparty.config
 * @version:1.0
 */

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AliyunConfig
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/19
 */

@Configuration
public class AliyunConfig {

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Bean
    public IAcsClient acsClient() {
        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

}

 