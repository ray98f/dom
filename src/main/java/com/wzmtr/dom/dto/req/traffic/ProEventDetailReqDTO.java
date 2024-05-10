package com.wzmtr.dom.dto.req.traffic;

import com.wzmtr.dom.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据请求类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProEventDetailReqDTO extends BaseEntity {

    /**
     * 生产作业ID
     * */
    private String infoId;

    /**
     * 记录内容
     */
    private String eventDesc;

    /**
     * 节点日期时间
     */
    private String eventTime;


}
