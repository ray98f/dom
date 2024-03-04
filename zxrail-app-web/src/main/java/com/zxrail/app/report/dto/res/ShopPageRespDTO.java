package com.zxrail.app.report.dto.res;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.zxrail.app.report.dto.ShopBaseDTO;

import java.time.LocalDateTime;

/**
 * PageDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShopPageRespDTO extends ShopBaseDTO {

    /**
     * id
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
}
