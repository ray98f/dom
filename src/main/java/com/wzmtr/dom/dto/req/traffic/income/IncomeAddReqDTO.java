package com.wzmtr.dom.dto.req.traffic.income;

import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.wzmtr.dom.dataobject.traffic.IncomeRecordDO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import com.wzmtr.dom.entity.BaseEntity;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.DateUtils;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 17:46
 */
@Data
@Slf4j
public class IncomeAddReqDTO extends BaseEntity {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("运营总收益")
    private IncomeListResDTO totalIncomeList;
    @ApiModelProperty(value = "现金")
    private Double type1Income;
    @ApiModelProperty(value = "一票通收益")
    private Double type2Income;
    @ApiModelProperty(value = "一卡通收益")
    private Double type3Income;
    @ApiModelProperty(value = "二维码收益")
    private Double type4Income;
    @ApiModelProperty(value = "银联收益")
    private Double type5Income;
    @ApiModelProperty(value = "划账数S1")
    private Double s1Remittance;
    @ApiModelProperty(value = "划账数S2")
    private Double s2Remittance;
    @ApiModelProperty(value = "平均票价S2")
    private Double s2AvgFare;
    @ApiModelProperty(value = "平均票价现网")
    private Double wiringAvgFare;
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;
    @ApiModelProperty(value = "数据类型")
    private String dataType;

    private String dataDate;

    public IncomeRecordDO toDO(IncomeAddReqDTO req) {
        IncomeListResDTO totalIncomeList1 = req.getTotalIncomeList();
        IncomeRecordDO convert = new IncomeRecordDO();
        if (null != totalIncomeList1){
            convert.setDayIncome(totalIncomeList1.getDayIncome());
            convert.setMonthIncome(totalIncomeList1.getMonthIncome());
            convert.setYearIncome(totalIncomeList1.getYearIncome());
        }
        String id = req.getId();
        convert.setId(StringUtils.isNotEmpty(id) ? id : TokenUtils.getUuId());
        convert.setType1Income(req.getType1Income());
        convert.setType2Income(req.getType2Income());
        convert.setType3Income(req.getType3Income());
        convert.setType4Income(req.getType4Income());
        convert.setType5Income(req.getType5Income());
        convert.setS1Remittance(req.getS1Remittance());
        convert.setDataType(req.getDataType());
        convert.setS2Remittance(req.getS2Remittance());
        convert.setS2AvgFare(req.getS2AvgFare());
        convert.setWiringAvgFare(req.getWiringAvgFare());
        convert.setStartDate(DateUtils.parseDate(req.getStartDate()));
        convert.setEndDate(DateUtils.parseDate(req.getEndDate()));
        return convert;
    }
}
