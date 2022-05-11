package com.ren.song;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.ren")
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.ren.song.mapper")
public class SongApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongApplication.class, args);
    }
}
