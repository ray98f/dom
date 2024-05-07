package com.wzmtr.dom.dto.req.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报表状态修改请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/24
 */
@Data
public class ReportUpdateReqDTO {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 日报状态:0草稿,1审核中,2审核通过
     */
    @ApiModelProperty(value = "日报状态:0草稿,1审核中,2审核通过")
    private String status;

    /**
     * 编辑人
     */
    @ApiModelProperty(value = "编辑人")
    private String updateBy;

}
