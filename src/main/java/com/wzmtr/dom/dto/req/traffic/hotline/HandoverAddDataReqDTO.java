package com.wzmtr.dom.dto.req.traffic.hotline;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Li.Wang
 * Date: 2024/3/27 15:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HandoverAddDataReqDTO extends BaseEntity {
    /**
     * 记录ID
     */
    @ApiModelProperty("记录ID")
    private String recordId;
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
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
}
