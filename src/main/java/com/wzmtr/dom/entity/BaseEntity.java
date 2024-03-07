package com.wzmtr.dom.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表通用参数类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 新增时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createDate;

    /**
     * 新增人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_date", fill = FieldFill.INSERT, update = "NOW()")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date updateDate;

    /**
     * 修改人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT)
    private String updateBy;

    /**
     * 删除标记
     */
    @JsonIgnore
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;
}
