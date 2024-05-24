package com.wzmtr.dom.dto.res.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 组织机构成员结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class MemberResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 父id路径
     */
    @ApiModelProperty(value = "父id路径")
    @JsonIgnore
    private String parentId;

    /**
     * 父id路径
     */
    @ApiModelProperty(value = "父id路径")
    @JsonIgnore
    private String parentIds;

    /**
     * 所属组织
     */
    @ApiModelProperty(value = "所属组织")
    private String orgPath;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String postName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 办公地点
     */
    @ApiModelProperty(value = "办公地点")
    private String room;

    /**
     * 是否显示手机号. 0否, 1是
     */
    @ApiModelProperty(value = "是否显示手机号. 0否, 1是")
    private String showMobileFlag;

    /**
     * 性别 0 未知 1 男 2 女
     */
    @ApiModelProperty(value = "性别 0 未知 1 男 2 女")
    private String sex;

    /**
     * 头像图片路径
     */
    @ApiModelProperty(value = "头像图片路径")
    private String avatarPic;

    /**
     * 工作状态
     */
    @ApiModelProperty(value = "工作状态")
    private String workStatus;

    /**
     * 帐号状态
     */
    @ApiModelProperty(value = "帐号状态")
    private String isLock;

    /**
     * 账号有效期
     */
    @ApiModelProperty(value = "账号有效期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lockTime;

    /**
     * 内部eip角色
     */
    @ApiModelProperty(value = "内部eip角色")
    private String eipRole;
}
