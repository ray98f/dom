package com.zxrail.app.report.dto.req;

import com.zxrail.app.report.dto.ShopBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * UpdateReqDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShopUpdateReqDTO extends ShopBaseDTO {

    @NotNull(message = "修改id不能为空")
    private Long id;
}
