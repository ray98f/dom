package com.wzmtr.dom.dto.res.traffic.income;

import com.wzmtr.dom.entity.BaseEntity;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.dataobject.traffic.IncomeRecordDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IncomeDetailResDTO extends BaseEntity {
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

    public static IncomeDetailResDTO buildRes(IncomeRecordDO incomeRecordDO){
        IncomeDetailResDTO convert = BeanUtils.convert(incomeRecordDO, IncomeDetailResDTO.class);
        IncomeListResDTO incomeListResDTO = new IncomeListResDTO();
        incomeListResDTO.setDayIncome(incomeRecordDO.getDayIncome());
        incomeListResDTO.setMonthIncome(incomeRecordDO.getMonthIncome());
        incomeListResDTO.setYearIncome(incomeRecordDO.getYearIncome());
        incomeListResDTO.setDataDate(incomeRecordDO.getDataDate());
        convert.setTotalIncomeList(incomeListResDTO);
        return convert;
    }
}
