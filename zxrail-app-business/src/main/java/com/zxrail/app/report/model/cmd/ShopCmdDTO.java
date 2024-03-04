package com.zxrail.app.report.model.cmd;

import com.zxrail.app.report.enums.EnableEnum;
import lombok.Data;

/**
 * CmdDTO
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Data
public class ShopCmdDTO {


    /**
     * 店铺id
     */
    private Long id;


    /**
     * 商铺名称
     */
    private String name;


    /**
     * 商铺地址
     */
    private String address;


    /**
     * 店铺成本
     */
    private Long cost;


    /**
     * 商铺分类
     */
    private String category;


    /**
     * 状态;1:启用;0:禁用
     */
    private EnableEnum status;

}
