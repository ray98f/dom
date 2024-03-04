package com.zxrail.app.report.controller.minio;

import com.zxrail.app.report.dto.res.FileResDTO;
import com.zxrail.app.report.service.MinIOService;
import com.zxrail.app.report.utils.__BeanUtil;
import com.zxrail.framework.common.base.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * minIO
 * Author: Li.Wang
 * Date: 2023/8/11 15:32
 */
@RestController
@RequestMapping("/minIO")
public class MinIOController {


    @Autowired
    private MinIOService minIOService;

    /**
     * 文件上传
     * @param file
     * @param bucketCode
     * @return
     */
    @PostMapping("/upload")
    public ApiResponse<FileResDTO> upload(@RequestParam MultipartFile file, String bucketCode) {
        return ApiResponse.ok(__BeanUtil.convert(minIOService.upload(file, bucketCode), FileResDTO.class));
    }
}
