package com.zxrail.app.report.dto.res;

import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2023/12/25 9:31
 */
@Data
public class FileResDTO {
    private String oldName;
    private String fileName;
    private String url;
    private String recCreator;
    private String recCreateTime;
    private String bucket;
    private String id;
}
