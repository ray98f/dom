package com.wzmtr.dom.dataobject.traffic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("TRAFFIC_INCOME_RECORD")
@Data
public class IncomeRecordDO {
    @TableId("ID")
    private String id;
    private Double dayIncome;
    private Double weekIncome;
    private Double monthIncome;
    private Double yearIncome;
    private Double type1Income;
    private Double type2Income;
    private Double type3Income;
    private Double type4Income;
    private Double type5Income;
    private Double s1Remittance;
    private Double s2Remittance;
    private Double wiringAvgFare;
    private Double s2AvgFare;
    private Date dataDate;
    private String dataType;
    private Date startDate;
    private Date endDate;
    private String delFlag;
    private String version;
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;

}