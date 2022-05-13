# recl-music-server



1.  项目背景

github开源音乐平台，原本为SpringBoot单一应用，比较简单；个人采用Nacos + Dubbo + Redis + MybatisPlus进行重构，重构也是为了学习dubbo。简单地用redis做了缓存，没有引入分布式锁之类的东西，加入分布式锁或其他技术会有一种炮弹打蚊子的感觉；熟悉前端结构花了两天，后端目前只花了一天左右时间重构，只完成了每个模块的核心接口，后期慢慢完善。

2.  技术架构

采用SpringBoot + Dubbo + Nacos + Redis + MybatisPlus

3.  项目模块


- recl-collection
- recl-comment
- recl-common
- recl-interface
- recl-oss
- recl-rank
- recl-singer
- recl-song
- recl-song-list
- recl-ucenter

原项目后端结构图：

![输入图片说明](https://recl-edu.oss-cn-beijing.aliyuncs.com/recl-music/img/singerPic/1560014170181tou.jpg)


我重构后的结构图

![输入图片说明](https://github.com/recl-rzy/recl-music/blob/master/QQ%E5%9B%BE%E7%89%8720220513211549.png)


### recl-common

所有服务的公共模块,包含两个子模块common_utils、common_base,common_utils包含了统一响应结果集的封装，common_base包含swagger-ui、dubbo、redis、MybatisPlus的配置

### recl-oss

统一文件上传模块，所有音频及图片均由阿里云OSS存储，OSS核心上传部分如下：


```
package com.ren.oss.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: OssUploadFile
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/13 14:58
 * @Version: v1.0
 */


public class OssUploadFile {

    public static String upload(MultipartFile file, String prefix) {

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
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(1, 6);

            //生成唯一文件名
            fileName = prefix + uuid + fileName;

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回文件访问路径url
            return "/" + fileName;

        } catch (Exception e) {

            e.printStackTrace();
            return "";
        }
    }
}
```


### recl-collection

歌曲收藏模块


### recl-comment

歌单评论模块


### recl-interface

远程调用的接口模块


### recl-rank

歌曲、歌单评分模块


### recl-singer

歌手模块


### recl-song

歌曲模块


### recl-song-list

歌单模块


### recl-ucenter

用户模块


4.Nacos-config统一管理配置文件

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513211723.png)

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513211745.png)


5.前端挺优雅

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513212050.png)

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513212117.png)

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513212724.png)

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513212754.png)

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513212837.png)


6.熟悉前端整体结构的熟悉花了很长时间，前后端接口整合还好。但跨域问题把人折磨透了，因为原本项目的音频、图片资源放在了项目本地目录下，但个人觉得麻烦直接将所有文件迁移到OSS，前端访问、特别是下载出现跨域问题，最终通过服务代理才解决

![输入图片说明](QQ%E5%9B%BE%E7%89%8720220513223351.png)













