package com.ren.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName: ReclOssService
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 19:35
 * @Version: v1.0
 */

public interface ReclOssService {

    String uploadFileAvatarImg(MultipartFile file);

    String uploadFileMusic(MultipartFile file);

    String uploadFileSingerImg(MultipartFile file);

    String uploadFileSongListImg(MultipartFile file);

    String uploadFileSongImg(MultipartFile file);
}
