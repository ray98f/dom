package com.wzmtr.dom.dto.req.traffic.hotline;

import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import com.wzmtr.dom.utils.BeanUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:03
 */
@Data
public class HotLineImportantAddReqDTO {

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
     * 数据所属日期
     */
    @ApiModelProperty("数据所属日期")
    private Date dataDate;
    /**
     * 数据类型 1日报 2周报 3月报
     */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;
    /**
     * 数据起始日期
     */
    @ApiModelProperty("数据起始日期")
    private Date startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    private Date endDate;



    public TrafficHotlineImportantDO toDO(HotLineImportantAddReqDTO req) {
        return BeanUtils.convert(req, TrafficHotlineImportantDO.class);
    }
}
