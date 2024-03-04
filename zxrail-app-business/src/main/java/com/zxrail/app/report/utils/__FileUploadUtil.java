package com.zxrail.app.report.utils;

import com.zxrail.framework.common.utils.DateUtils;
import com.zxrail.framework.common.utils.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

public class __FileUploadUtil {
    /**
     * 默认大小 10M
     */
    public static final long DEFAULT_MAX_SIZE = 10 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;


    /**
     * 编码文件名
     */
    public static String extractFilename(MultipartFile file) {
        String extension = getExtension(file);
        return DateUtils.datePath() + "/" + UUID.randomUUID() + "." + extension;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = __MimeTypeUtil.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }
}