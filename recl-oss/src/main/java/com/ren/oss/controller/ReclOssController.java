package com.ren.oss.controller;

import com.ren.dubboservice.UserService;
import com.ren.oss.service.ReclOssService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Reference(parameters = {"unicast", "false"}, mock = "return ''")
    UserService userService;

    @ApiOperation(value = "头像上传")
    @PostMapping("/avatar")
    public Result uploadAvatarImg(MultipartFile file, @RequestParam String id) {
        String url = reclOssService.uploadFileAvatarImg(file);
        boolean res = userService.updateUserAvatar(id, url);
        if(res) return Result.ok()
                .message("头像修改成功")
                .data("url", url);
        else return Result.error()
                .message("头像修改失败，请稍后再试!");
    }

    @ApiOperation(value = "音乐上传")
    @PostMapping("/music")
    public String uploadMusic(MultipartFile file) {
        return reclOssService.uploadFileMusic(file);
    }

    @ApiOperation(value = "歌手封面上传")
    @PostMapping("/singer-pic")
    public String uploadSingerPicImg(MultipartFile file) {
        return reclOssService.uploadFileSingerImg(file);
    }

    @ApiOperation(value = "歌单封面上穿")
    @PostMapping("/song-list-pic")
    public String uploadSongListPic(MultipartFile file) {
        return reclOssService.uploadFileSongListImg(file);
    }

    @ApiOperation(value = "歌曲封面上传")
    @PostMapping("/song-pic")
    public String uploadSongPic(MultipartFile file) {
        return reclOssService.uploadFileSongImg(file);
    }
}
