package com.ren.base;

import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: DubboConfig
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 17:57
 * @Version: v1.0
 */

@Configuration
public class DubboConfig {

    /**
     * 消费者不主动监督生产者的服务状态
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(40000);
        return consumerConfig;
    }
}
