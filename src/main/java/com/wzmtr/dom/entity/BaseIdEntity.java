package com.wzmtr.dom.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * id类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class BaseIdEntity {

    /**
     * id
     */
    @NotNull(message = "32000006")
    private String id;
}
