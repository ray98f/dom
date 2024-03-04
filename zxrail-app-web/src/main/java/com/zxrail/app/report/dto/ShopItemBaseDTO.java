package com.zxrail.app.report.dto;

;

import lombok.Data;

/**
 * baseDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
public class ShopItemBaseDTO {


    /**
     * 店铺id
     */
    protected Long shopId;


    /**
     * 商品名称
     */
    protected String name;


    /**
     * 商品价格
     */
    protected Long price;


    /**
     * 商品说明
     */
    protected String itemDesc;


    /**
     * 状态;1:启用;0:禁用
     */
    protected Integer status;


    /**
     * 图片id
     */
    protected Long picId;

}