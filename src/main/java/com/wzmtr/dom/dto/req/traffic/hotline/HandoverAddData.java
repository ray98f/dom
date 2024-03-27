package com.wzmtr.dom.dto.req.traffic.hotline;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2024/3/27 15:46
 */
@Data
public class HandoverAddData {
    /**
     * id
     */
    @TableId("ID")
    private String id;
    /**
     * 主要内容
     */
    @ApiModelProperty("主要内容")
    private String mainContent;
    /**
     * 来源
     */
    @ApiModelProperty("来源")
    private String source;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Long count;
    /**
     * 处理结果
     */
    @ApiModelProperty("处理结果")
    private String result;
}
