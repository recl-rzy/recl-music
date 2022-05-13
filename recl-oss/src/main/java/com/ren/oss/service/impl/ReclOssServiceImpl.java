package com.ren.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.ren.oss.service.ReclOssService;
import com.ren.oss.utils.OssUploadFile;
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
    public String uploadFileAvatarImg(MultipartFile file) {

        //生成文件名前缀
        String prefix = "recl-music/img/avatarImages/";
        //返回生产的Url
        return OssUploadFile.upload(file, prefix);
    }

    @Override
    public String uploadFileMusic(MultipartFile file) {

        //生成文件名前缀
        String prefix = "recl-music/music/";
        //返回生产的Url
        return OssUploadFile.upload(file, prefix);
    }

    @Override
    public String uploadFileSingerImg(MultipartFile file) {
        //生成文件名前缀
        String prefix = "recl-music/img/singerPic/";
        //返回生产的Url
        return OssUploadFile.upload(file, prefix);
    }

    @Override
    public String uploadFileSongListImg(MultipartFile file) {
        //生成文件名前缀
        String prefix = "recl-music/img/songListPic/";
        //返回生产的Url
        return OssUploadFile.upload(file, prefix);
    }

    @Override
    public String uploadFileSongImg(MultipartFile file) {
        //生成文件名前缀
        String prefix = "recl-music/img/songPic/";
        //返回生产的Url
        return OssUploadFile.upload(file, prefix);
    }

}
