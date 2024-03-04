package com.zxrail.app.report.service;

import com.zxrail.app.report.model.FileDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Li.Wang
 * Date: 2023/12/25 8:44
 */
public interface MinIOService {

    FileDTO upload(MultipartFile file, String bucketCode);
}
