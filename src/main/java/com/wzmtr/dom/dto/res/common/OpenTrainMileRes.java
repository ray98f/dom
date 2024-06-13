package com.wzmtr.dom.dto.res.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/24 16:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenTrainMileRes {

    private Integer sumDailyWorkMile;
    private Integer sumDailyMile;
}
