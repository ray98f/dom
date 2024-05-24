package com.wzmtr.dom.entity;

import lombok.Data;

/**
 * 文件类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Data
public class File {
    /**
     * 文件原始名称
     */
    private String oldName;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 链接
     */
    private String url;
    /**
     * 新增人
     */
    private String createBy;
    /**
     * 新增时间
     */
    private String createDate;
    /**
     * 文件桶编号
     */
    private String bucket;
    /**
     * id
     */
    private String id;
}
