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
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 公共分类-文件管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

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
        if (!minioUtils.bucketExists(bucket)) {
            minioUtils.makeBucket(bucket);
        }
        String oldName = file.getOriginalFilename();
        String fileName = FileUploadUtils.extractFilename(file);
        try {
            @Cleanup
            InputStream inputStream = file.getInputStream();
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            client.putObject(args);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "上传失败");
        }
        String url = minioConfig.getImgPath() + "/" + bucket + "/" + fileName;
        FileReqDTO build = FileReqDTO.builder()
                .bucket(bucket)
                .fileName(fileName)
                .id(TokenUtils.getUuId())
                .oldName(oldName)
                .url(url)
                .createDate(DateUtils.getCurrentTime())
                .createBy(TokenUtils.getCurrentPersonId())
                .build();
        fileMapper.insertFile(build);
        return fileMapper.getFile(url, bucket, oldName);
    }
}
