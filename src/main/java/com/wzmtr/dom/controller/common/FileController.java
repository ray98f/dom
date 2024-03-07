package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.entity.File;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.service.common.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共分类-文件管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@RestController
@RequestMapping("/file")
@Api(tags = "公共分类-文件管理")
public class FileController {

    /**
     * 文件大小限制 50MB
     */
    private static final long NUM = 50L * 1024L * 1024L;

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file 文件
     * @param bucketCode 文件桶编号
     * @return 文件信息
     */
    @ApiOperation(value = "上传")
    @PostMapping("/upload")
    public DataResponse<File> upload(@RequestParam MultipartFile file, String bucketCode) {
        if (file.getSize() > NUM) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "上传文件大小超出限制");
        }
        return DataResponse.of(fileService.upload(file, bucketCode));
    }
}
