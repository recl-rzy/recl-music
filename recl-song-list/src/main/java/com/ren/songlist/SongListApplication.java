package com.ren.songlist;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: SongListApplication
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/11 23:18
 * @Version: v1.0
 */


@ComponentScan(basePackages = "com.ren")
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.ren.songlist.mapper")
public class SongListApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongListApplication.class, args);
    }
}
