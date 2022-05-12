package com.ren.collection;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: CollectionApplication
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 12:04
 * @Version: v1.0
 */

@MapperScan("com.ren.collection.mapper")
@ComponentScan(basePackages = "com.ren")
@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication
public class CollectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectionApplication.class, args);
    }
}
