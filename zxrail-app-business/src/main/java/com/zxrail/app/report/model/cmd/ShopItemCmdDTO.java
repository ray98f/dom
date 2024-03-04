package com.zxrail.app.report.model.cmd;

import lombok.Data;

/**
 * CmdDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
public class ShopItemCmdDTO {


    /**
     * 商品id
     */
    private Long id;


    /**
     * 店铺id
     */
    private Long shopId;


    /**
     * 商品名称
     */
    private String name;


    /**
     * 商品价格
     */
    private Long price;


    /**
     * 商品说明
     */
    private String itemDesc;


    /**
     * 状态;1:启用;0:禁用
     */
    private Integer status;


    /**
     * 图片id
     */
    private Long picId;

}
