package com.zxrail.app.report.model.query;

import com.zxrail.app.report.enums.EnableEnum;
import com.zxrail.framework.db.base.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * QueryDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShopQueryDTO extends PageParam {

    /**
     *
     * 状态;1:启用;0:禁用
     * @see EnableEnum#getCode()
     */
    protected EnableEnum status;
}
