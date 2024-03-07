package com.wzmtr.dom.service.common;

import com.wzmtr.dom.entity.File;
import org.springframework.web.multipart.MultipartFile;

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
}
