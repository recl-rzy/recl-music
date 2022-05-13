package com.ren.ucenter;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: UcenterApplication
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/13 11:39
 * @Version: v1.0
 */

@ComponentScan(basePackages = "com.ren")
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.ren.ucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
