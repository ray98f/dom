package com.zxrail.app.report.dto;

;

import com.zxrail.app.report.enums.EnableEnum;
import lombok.Data;

/**
 * baseDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
public class ShopBaseDTO {


    /**
     * 商铺名称
     */
    protected String name;


    /**
     * 商铺地址
     */
    protected String address;


    /**
     * 店铺成本
     */
    protected Long cost;


    /**
     * 商铺分类
     */
    protected String category;


    /**
     *
     * 状态;1:启用;0:禁用
     * @see EnableEnum#getCode()
     */
    protected EnableEnum status;

}