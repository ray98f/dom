package com.wzmtr.dom.dto.res.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@Data
public class EventCountResDTO {


    /**
     * 归口专业
     * */
    @ApiModelProperty(value = "归口专业")
    private String majorType;

    /**
     * 数量
     * */
    @ApiModelProperty(value = "数量")
    private Integer count;

}
