package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.IncomeRecordDO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeListReqDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 9:48
 */
@Mapper
@Repository
public interface IncomeRecordMapper extends BaseMapper<IncomeRecordDO> {
    Page<IncomeListResDTO> list(Page<Object> page, @Param("req") IncomeListReqDTO reqDTO);

    /**
     * 判断收益总体情况是否已存在
     * @param reqDTO 收益总体情况参数
     * @return 是否已存在
     */
    Integer checkExist(IncomeAddReqDTO reqDTO);

    IncomeRecordDO selectDetailById(IncomeDetailReqDTO reqDTO);

    void add(IncomeAddReqDTO req);

    /**
     * 收益数据更新
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModify(String dataType,String startDate,String endDate);

    /**
     * 收益数据更新
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModifyByDaily(String dataType,String startDate,String endDate);
}
