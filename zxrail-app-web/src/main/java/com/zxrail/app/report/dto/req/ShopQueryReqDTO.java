package com.zxrail.app.report.dto.req;

import com.zxrail.app.report.enums.EnableEnum;
import com.zxrail.framework.db.base.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * QueryReqDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShopQueryReqDTO extends PageParam {

    /**
     *
     * 状态;1:启用;0:禁用
     * @see EnableEnum#getCode()
     */
    protected EnableEnum status;
}
