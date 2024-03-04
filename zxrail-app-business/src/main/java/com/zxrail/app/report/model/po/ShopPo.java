package com.zxrail.app.report.model.po;

import com.mybatisflex.annotation.RelationOneToMany;
import com.zxrail.app.report.enums.EnableEnum;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;

import com.zxrail.framework.db.base.BaseTenantPo;
import lombok.EqualsAndHashCode;

import java.util.List;

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
@Table(value = "t_shop")
public class ShopPo extends BaseTenantPo {


    @Column(value = "name")
    private String name;


    @Column(value = "address")
    private String address;


    @Column(value = "cost")
    private Long cost;


    @Column(value = "category")
    private String category;

    @Column(value = "status")
    private EnableEnum status;


    @RelationOneToMany(selfField = "id", targetField = "shopId")
    private List<ShopItemPo> shopItemList;

}
