package com.wzmtr.dom.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author frp
 */
@Data
public class BaseIdEntity {

    /**
     * id
     */
    @NotNull(message = "32000006")
    private String id;
}
