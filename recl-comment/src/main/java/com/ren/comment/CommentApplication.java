package com.ren.comment;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: CommentApplication
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/11 19:21
 * @Version: v1.0
 */

@MapperScan("com.ren.comment.mapper")
@ComponentScan(basePackages = "com.ren")
@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class, args);
    }
}
