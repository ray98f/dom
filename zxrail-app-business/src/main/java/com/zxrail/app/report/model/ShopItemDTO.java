package com.zxrail.app.report.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * dto
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
public class ShopItemDTO {

    /**
     * 商品id
     */
    protected Long id;


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
     * 创建者
     */
    protected String createBy;


    /**
     * 创建时间
     */
    protected LocalDateTime createTime;


    /**
     * 更新者
     */
    protected String updateBy;


    /**
     * 更新时间
     */
    protected LocalDateTime updateTime;


    /**
     * 状态;1:启用;0:禁用
     */
    protected Integer status;


    /**
     * 图片id
     */
    protected Long picId;


}
