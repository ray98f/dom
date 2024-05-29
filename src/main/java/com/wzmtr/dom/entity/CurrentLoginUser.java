package com.wzmtr.dom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class CurrentLoginUser implements Serializable {

    private String personId;

    private String personNo;

    private String personName;

    private String companyId;

    private String companyName;

    private String companyAreaId;

    private String officeId;

    private String officeName;

    private String officeAreaId;

    private String email;

    private String mobile;

    private String phone;

    private String names;

    /**
     * 所属车站编码
     * */
    private String stationCode;

    public CurrentLoginUser() {}
}
