package com.ren.oss.controller;

import com.ren.oss.service.ReclOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: ReclOssController
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 19:32
 * @Version: v1.0
 */

@Api(tags = "aliyun文件上传")
@RestController
@RequestMapping("/oss")
@CrossOrigin
public class ReclOssController {

    @Autowired
    ReclOssService reclOssService;

    @ApiOperation(value = "图片上传")
    @PostMapping("/img")
    public String uploadImg(MultipartFile file) {
        return reclOssService.uploadFileImg(file);
    }

    @ApiOperation(value = "音乐上传")
    @PostMapping("/music")
    public String uploadMusic(MultipartFile file) {
        return reclOssService.uploadFileMusic(file);
    }
}
