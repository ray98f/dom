package com.zxrail.app.report.model.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Li.Wang
 * Date: 2023/12/25 9:31
 */
@Data
@Table(value = "T_FILE")
public class FilePO {
    @Id
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
    private String oldName;
    private String fileName;
    private String url;
    private String bucket;
}
