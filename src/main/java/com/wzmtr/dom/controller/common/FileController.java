package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.entity.File;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.service.common.FileService;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
            throw new CommonException(ErrorCode.FILE_BIG);
        }
        return DataResponse.of(fileService.upload(file, bucketCode));
    }

    /**
     * 清空桶
     * @param bucketCode 桶名
     * @return 成功
     * @throws ServerException 异常
     * @throws InsufficientDataException 异常
     * @throws ErrorResponseException 异常
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     * @throws InvalidResponseException 异常
     * @throws XmlParserException 异常
     * @throws InternalException 异常
     */
    @ApiOperation(value = "清空桶")
    @GetMapping("/clear")
    public DataResponse<T> clear(@RequestParam String bucketCode) throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        fileService.clear(bucketCode);
        return DataResponse.success();
    }
}
