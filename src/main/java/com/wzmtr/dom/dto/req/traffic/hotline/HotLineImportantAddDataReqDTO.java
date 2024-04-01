package com.wzmtr.dom.dto.req.traffic.hotline;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:03
 */
@Data
public class HotLineImportantAddDataReqDTO {

    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Long count;
    /**
     * 主要内容
     */
    @ApiModelProperty("主要内容")
    private String content;
    /**
     * 处理结果
     */
    @ApiModelProperty("处理结果")
    private String result;
    /**
     * 关键词
     */
    @ApiModelProperty("关键词")
    private String keyword;
    /**
     * 热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他
     */
    @ApiModelProperty("热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他")
    private String hotlineType;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
}
