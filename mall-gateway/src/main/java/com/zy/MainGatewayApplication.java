package com.zy;/**
 * @Author:zhoayu
 * @Date:2024/11/10 20:33
 * @Description:com.zy
 * @version:1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName MainGatewayApplication
 * @Description //TODO 
 * @Author zhaoyu
 * @Date 2024/11/10
 */

@SpringBootApplication
@EnableDiscoveryClient
public class MainGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainGatewayApplication.class, args);
    }

}

 