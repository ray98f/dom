package com.wzmtr.dom.enums;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/21 17:09
 */
public enum TrafficReportRole {

    //TODO 后续补全对应角色
    REPORT_TYPE_0("0", "ADMIN,DEPT_HEAD"),
    REPORT_TYPE_1("1", "ACC,TICKET_ENGINEER,TICKET_CENTER_HEAD"),
    REPORT_TYPE_2("2", "HOTLINE_PERSON,HOTLINE_ENGINEER"),
    REPORT_TYPE_3("3", "DUTY_STATION_MASTER,STATION_MASTER");


    /**
     * 报表类型
     * */
    private String reportType;

    /**
     * 可见角色
     * */
    private String roles;

    TrafficReportRole(String reportType, String roles) {
        this.reportType = reportType;
        this.roles = roles;
    }

    public String reportType() {
        return this.reportType;
    }

    public String roles() {
        return this.roles;
    }
}
