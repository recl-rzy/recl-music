package com.ren.singer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: SingerApplication
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 10:51
 * @Version: v1.0
 */

@ComponentScan(basePackages = "com.ren")
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.ren.singer.mapper")
public class SingerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingerApplication.class, args);
    }
}
