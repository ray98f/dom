package com.zxrail.app.report.model;

import com.zxrail.app.report.enums.EnableEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * dto
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
public class ShopDTO {

    /**
     * 店铺id
     */
    protected Long id;


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
    protected EnableEnum status;


}
