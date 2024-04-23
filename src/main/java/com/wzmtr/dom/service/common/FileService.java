package com.wzmtr.dom.service.common;

import com.wzmtr.dom.entity.File;
import io.minio.errors.*;
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
public interface FileService {
    /**
     * 文件上传
     * @param file 文件
     * @param bucketCode 文件桶编号
     * @return 文件信息
     */
    File upload(MultipartFile file, String bucketCode);

    /**
     * 清空桶
     * @param bucketCode 桶名
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
    void clear(String bucketCode) throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
