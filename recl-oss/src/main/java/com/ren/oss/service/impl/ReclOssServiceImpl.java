package com.ren.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.ren.oss.service.ReclOssService;
import com.ren.oss.utils.PropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: ReclOssServiceImpl
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 19:36
 * @Version: v1.0
 */

@Service
public class ReclOssServiceImpl implements ReclOssService {

    @Override
    public String uploadFileImg(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = PropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = PropertiesUtils.KEY_ID;
        String accessKeySecret = PropertiesUtils.KEY_SECRET;
        String bucketName = PropertiesUtils.BUCKET_NAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //生成随机UUID
            //String uuid = UUID.randomUUID().toString().replace("-", "");

            //fileName = uuid + fileName;
            fileName = "recl-music/img/" + fileName;

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;

            return url;

        } catch (OSSException e) {

            e.printStackTrace();
            return null;
        } catch (ClientException e) {

            e.printStackTrace();
            return null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadFileMusic(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = PropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = PropertiesUtils.KEY_ID;
        String accessKeySecret = PropertiesUtils.KEY_SECRET;
        String bucketName = PropertiesUtils.BUCKET_NAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //生成随机UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");

            fileName = uuid + fileName;
            fileName = "recl-music/music/" + fileName;

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;

            return url;

        } catch (OSSException e) {

            e.printStackTrace();
            return null;
        } catch (ClientException e) {

            e.printStackTrace();
            return null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

}
