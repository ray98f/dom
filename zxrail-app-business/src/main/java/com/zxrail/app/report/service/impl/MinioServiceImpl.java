package com.zxrail.app.report.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zxrail.app.report.constant.ReportCols;
import com.zxrail.app.report.constant.ReportConstants;
import com.zxrail.app.report.errorcode.ReportErrorCode;
import com.zxrail.app.report.mapper.FileMapper;
import com.zxrail.app.report.model.FileDTO;
import com.zxrail.app.report.model.po.FilePO;
import com.zxrail.app.report.service.MinIOService;
import com.zxrail.app.report.utils.__BeanUtil;
import com.zxrail.app.report.utils.__FileUploadUtil;
import com.zxrail.app.report.utils.__MinioUtil;
import com.zxrail.app.report.utils.SnowFlakeIDGenerator;
import com.zxrail.framework.common.errorcode.ServiceException;
import com.zxrail.framework.config.properties.FileProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Author: Li.Wang
 * Date: 2023/8/11 15:38
 */
@Service
@Slf4j
public class MinioServiceImpl extends ServiceImpl<FileMapper, FilePO> implements MinIOService {

    @Autowired
    private __MinioUtil minioUtil;
    @Autowired
    private MinioClient client;
    @Autowired
    private FileProperties fileProperties;

    @Override
    public FileDTO upload(MultipartFile file, String bucket) {
        if (file.getSize() > ReportConstants.FILE_SIZE_LIMIT) {
            throw new ServiceException(ReportErrorCode.APP_RUNTIME_EXCEPTION_ERROR, "上传文件大小超出限制");
        }
        if (!minioUtil.bucketExists(bucket)) {
            minioUtil.makeBucket(bucket);
        }
        String oldName = file.getOriginalFilename();
        String fileName = __FileUploadUtil.extractFilename(file);
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
            throw new ServiceException(ReportErrorCode.APP_RUNTIME_EXCEPTION_ERROR, "上传失败");
        }
        String url = fileProperties.getImgPath() + "/" + bucket + "/" + fileName;
        FileDTO build = FileDTO.builder()
                .bucket(bucket)
                .fileName(fileName)
                .id(SnowFlakeIDGenerator._nextId())
                .oldName(oldName)
                .url(url)
                .createTime(LocalDateTime.now())
                .createBy(StpUtil.getLoginId().toString())
                .build();
        mapper.insert(__BeanUtil.convert(build, FilePO.class));
        // 返回文件详情
        FilePO filePO = mapper.selectOneByQuery(new QueryWrapper().eq(ReportCols.URL, url).eq(ReportCols.BUCKET, bucket).eq(ReportCols.OLD_NAME, oldName));
        return __BeanUtil.convert(filePO, FileDTO.class);
    }
}
