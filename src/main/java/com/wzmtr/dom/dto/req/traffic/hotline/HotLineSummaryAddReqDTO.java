package com.wzmtr.dom.dto.req.traffic.hotline;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineSummaryDO;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.utils.BeanUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:03
 */
@Data
public class HotLineSummaryAddReqDTO {

    /**
     * id
     */
    @TableId("ID")
    private String id;
    /**
     * 总投诉
     */
    @ApiModelProperty("总投诉")
    private Long complaintTotal;
    /**
     * 总表扬
     */
    @ApiModelProperty("总表扬")
    private Long praiseTotal;
    /**
     * 接听总量
     */
    @ApiModelProperty("接听总量")
    private Long answerTotal;
    /**
     * app回复
     */
    @ApiModelProperty("app回复")
    private Long appAnswer;
    /**
     * 咨询
     */
    @ApiModelProperty("咨询")
    private Long consult;
    /**
     * 求助
     */
    @ApiModelProperty("求助")
    private Long resort;
    /**
     * 有责投诉
     */
    @ApiModelProperty("有责投诉")
    private Long type1Complaint;
    /**
     * 无责投诉
     */
    @ApiModelProperty("无责投诉")
    private Long type2Complaint;
    /**
     * 热线表扬
     */
    @ApiModelProperty("热线表扬")
    private Long type1Praise;
    /**
     * 车站表扬
     */
    @ApiModelProperty("车站表扬")
    private Long type2Praise;
    /**
     * 锦旗
     */
    @ApiModelProperty("锦旗")
    private Long pennant;
    /**
     * S1转接
     */
    @ApiModelProperty("S1转接")
    private Long s1Switch;
    /**
     * 其他
     */
    @ApiModelProperty("其他")
    private Long other;
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
     * 删除标识
     */
    @ApiModelProperty("删除标识")
    private String delFlag;
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private Date createDate;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String createBy;
    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    private Date updateDate;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private String updateBy;
    /**
     * 说明
     */
    @ApiModelProperty("说明")
    private String remark;


    public TrafficHotlineSummaryDO toDO(HotLineSummaryAddReqDTO req) {
        return BeanUtils.convert(req, TrafficHotlineSummaryDO.class);
    }
}
