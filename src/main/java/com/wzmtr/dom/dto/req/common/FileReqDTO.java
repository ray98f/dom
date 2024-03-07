package com.wzmtr.dom.dto.req.common;

import lombok.Builder;
import lombok.Data;

/**
 * 文件上传类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Data
@Builder
public class FileReqDTO {
    /**
     * id
     */
    private String id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件原始名称
     */
    private String oldName;
    /**
     * 文件桶编号
     */
    private String bucket;
    /**
     * 链接
     */
    private String url;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createDate;
}
