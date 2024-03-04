package com.zxrail.app.report.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: Li.Wang
 * Date: 2023/12/25 9:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String oldName;
    private String fileName;
    private String url;
    private String bucket;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
