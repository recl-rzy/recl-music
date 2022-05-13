package com.ren.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @ClassName: PropertiesUtils
 * @Project_Name: recl
 * @Author RZY
 * @Date: 2021/8/6 15:33
 * @Vertion: 2019.1
 */

@Component
public class PropertiesUtils implements InitializingBean {

    //ToDo 读取配置文件中的oss相关秘钥
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    public static String END_POINT;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {

        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
