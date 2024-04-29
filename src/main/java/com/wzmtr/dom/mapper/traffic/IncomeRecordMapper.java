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

    IncomeRecordDO selectDetailById(IncomeDetailReqDTO reqDTO);

    void add(IncomeAddReqDTO req);
}
