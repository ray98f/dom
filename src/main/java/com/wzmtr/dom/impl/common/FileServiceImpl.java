package com.wzmtr.dom.impl.common;

import com.wzmtr.dom.config.MinioConfig;
import com.wzmtr.dom.dto.req.common.FileReqDTO;
import com.wzmtr.dom.entity.File;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.common.FileMapper;
import com.wzmtr.dom.service.common.FileService;
import com.wzmtr.dom.utils.DateUtils;
import com.wzmtr.dom.utils.FileUploadUtils;
import com.wzmtr.dom.utils.MinioUtils;
import com.wzmtr.dom.utils.TokenUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 公共分类-文件管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${pro.name}")
    private String proName;

    @Autowired
    private MinioUtils minioUtils;

    @Autowired
    private MinioClient client;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public File upload(MultipartFile file, String bucket) {
        if (!minioUtils.bucketExists(proName)) {
            minioUtils.makeBucket(proName);
        }
        String oldName = file.getOriginalFilename();
        String fileName = FileUploadUtils.extractFilename(file, bucket);
        try {
            @Cleanup
            InputStream inputStream = file.getInputStream();
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(proName)
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            client.putObject(args);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "上传失败");
        }
        String url = minioConfig.getImgPath() + "/" + proName + "/" + fileName;
        FileReqDTO build = FileReqDTO.builder()
                .bucket(proName)
                .fileName(fileName)
                .id(TokenUtils.getUuId())
                .oldName(oldName)
                .url(url)
                .createDate(DateUtils.getCurrentTime())
                .createBy(TokenUtils.getCurrentPersonId())
                .build();
        fileMapper.insertFile(build);
        return fileMapper.getFile(url, proName, oldName);
    }

    @Override
    public void clear(String bucketCode) throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (minioUtils.bucketExists(bucketCode)) {
            minioUtils.clearBucket(bucketCode);
            minioUtils.removeBucket(bucketCode);
        }
    }
}
