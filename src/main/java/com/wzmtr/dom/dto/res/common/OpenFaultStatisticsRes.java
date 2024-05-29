package com.wzmtr.dom.dto.res.common;

import lombok.Data;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/24 16:42
 */
@Data
public class OpenFaultStatisticsRes {

    private String subjectCode;
    private String subjectName;
    private Integer faultNum;
}
