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

    String uploadFileImg(MultipartFile file);

    String uploadFileMusic(MultipartFile file);
}
