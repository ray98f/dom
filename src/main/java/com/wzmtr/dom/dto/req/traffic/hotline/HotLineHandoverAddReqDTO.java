package com.wzmtr.dom.dto.req.traffic.hotline;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineHandoverDO;
import com.wzmtr.dom.utils.BeanUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 17:07
 */

@Data
public class HotLineHandoverAddReqDTO {
    /**
     * id
     */
    @TableId("ID")
    private String id;
    /**
     * 热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他
     */
    @ApiModelProperty("热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他")
    private String hotlineType;
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
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;

    public TrafficHotlineHandoverDO toDO(HotLineHandoverAddReqDTO req) {
        return BeanUtils.convert(req, TrafficHotlineHandoverDO.class);
    }
}
