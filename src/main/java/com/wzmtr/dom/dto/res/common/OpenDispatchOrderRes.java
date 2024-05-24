package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/11 14:55
 */
@Data
public class OpenDispatchOrderRes {
    @ApiModelProperty("发令人")
    private String createdBy;
    @ApiModelProperty("发令时间")
    private String creationTime;
    @ApiModelProperty("修改人")
    private String modifiedBy;
    @ApiModelProperty("受命令处所*")
    private String dispatchorderReceiver;
    @ApiModelProperty("命令代码")
    private String orderCode;
    @ApiModelProperty("ID")
    private String dispatchorderId;
    @ApiModelProperty("命令状态")
    private String dispatchorderStatus;
    @ApiModelProperty("作业编码")
    private String workCode;
    @ApiModelProperty("PROC_INS_ID")
    private String procInsId;
    @ApiModelProperty("业务类型")
    private String dispatchorderType;
    @ApiModelProperty("受命令处所id")
    private String dispatchorderReceiverId;
    @ApiModelProperty("删除状态")
    private String deleteStatus;
    @ApiModelProperty("调度diamante")
    private String dispatchCode;
    @ApiModelProperty("命令内容")
    private String dispatchorderDesc;
    @ApiModelProperty("命令标题")
    private String dispatchorderTitle;
    @ApiModelProperty("线路")
    private String lineCode;
    @ApiModelProperty("修改时间")
    private String modifecationTime;
    @ApiModelProperty("是否限制时间")
    private String islimitedtime;
    @ApiModelProperty("N分钟回复")
    private Integer replyminutes;
    @ApiModelProperty("发令时间")
    private String orderTime;
    @ApiModelProperty("回复时间")
    private String replyTime;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("人员id")
    private String orderendgroupIds;
    @ApiModelProperty("交司机id")
    private String calldriverStationId;
    @ApiModelProperty("交司机名称")
    private String calldriverStation;
    @ApiModelProperty("涉及线路")
    private String sjlineCode;
}
