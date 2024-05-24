package com.wzmtr.dom.dto.res.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class UserCenterInfoResDTO {

    /**
     * 人员ID
     */
    @ApiModelProperty(value = "人员ID")
    private String id;

    /**
     * 头像
     */
    private String photo;

    /**
     * 头像大图
     */
    private String bigPhoto;

    /**
     * 头像小图
     */
    private String smallPhoto;

    /**
     * 人员编号
     */
    @ApiModelProperty(value = "人员编号")
    private String no;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 办公地点
     */
    private String room;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 组织机构名称
     */
    private String officeName;

    /**
     * 组织机构id
     */
    private String officeId;

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
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date hireDate;

    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    private Integer distanceDay;

    /**
     * 用户权限
     */
    @ApiModelProperty(value = "用户权限")
    private List<UserRoleResDTO> userRoles;
}
