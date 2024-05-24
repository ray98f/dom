package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 数据请求类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperateEventDetailReqDTO extends BaseEntity {

    /**
     * 运营事件ID
     * */
    private String eventId;

    /**
     * 记录内容
     */
    private String eventContent;

    /**
     * 节点日期时间
     */
    private String nodeTime;


}
