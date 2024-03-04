package com.zxrail.app.report.model.po;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;

import com.zxrail.framework.db.base.BaseTenantPo;
import lombok.EqualsAndHashCode;

/**
 * 实体类。
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_shop_item")
public class ShopItemPo extends BaseTenantPo {


    @Column(value = "shop_id")
    private Long shopId;


    @Column(value = "name")
    private String name;


    @Column(value = "price")
    private Long price;


    @Column(value = "item_desc")
    private String itemDesc;


    @Column(value = "status")
    private Integer status;


    @Column(value = "pic_id")
    private Long picId;


}
