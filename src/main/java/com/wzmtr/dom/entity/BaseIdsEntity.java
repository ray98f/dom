package com.wzmtr.dom.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ids类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class BaseIdsEntity {

    /**
     * ids
     */
    @NotNull(message = "32000006")
    private List<String> ids;
}
