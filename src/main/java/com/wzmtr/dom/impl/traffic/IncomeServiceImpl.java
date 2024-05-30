package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dataobject.traffic.IncomeRecordDO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeListReqDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.IncomeRecordMapper;
import com.wzmtr.dom.service.traffic.IncomeService;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRecordMapper incomeMapper;

    @Override
    public Page<IncomeListResDTO> list(IncomeListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<IncomeListResDTO> list = incomeMapper.list(reqDTO.of(), reqDTO);
        if (CollectionUtil.isEmpty(list.getRecords())) {
            return new Page<>();
        }
        return list;
    }

    @Override
    public IncomeDetailResDTO detail(IncomeDetailReqDTO req) {
        IncomeRecordDO incomeRecordDO = incomeMapper.selectDetailById(req);
        return IncomeDetailResDTO.buildRes(incomeRecordDO);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser,IncomeAddReqDTO reqDTO) {
        Integer result = incomeMapper.checkExist(reqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期收益总体情况数据已存在，无法重复新增");
        }
        reqDTO.setCreateBy(currentLoginUser.getPersonId());
        reqDTO.setUpdateBy(currentLoginUser.getPersonId());
        reqDTO.setId(TokenUtils.getUuId());
        incomeMapper.add(reqDTO);
        //周报/月报数据统计
        if(!CommonConstants.DATA_TYPE_DAILY.equals(reqDTO.getDataType())){
            autoModify(reqDTO.getDataType(),reqDTO.getStartDate(),reqDTO.getEndDate());
        }
    }


    @Override
    public void modify(CurrentLoginUser currentLoginUser, IncomeAddReqDTO reqDTO) {
        String id = Assert.notNull(reqDTO.getId(), "id不能为空");
        IncomeRecordDO now = incomeMapper.selectById(id);
        IncomeRecordDO incomeRecordDO = reqDTO.toDO(reqDTO);
        incomeRecordDO.setUpdateBy(currentLoginUser.getPersonId());
        incomeRecordDO.setVersion(String.valueOf(Integer.parseInt(now.getVersion()) + 1));
        incomeMapper.updateById(incomeRecordDO);
        if(CommonConstants.DATA_TYPE_DAILY.equals(now.getDataType())){
            autoModify(now.getDataType(),DateUtil.formatDate(now.getStartDate()),DateUtil.formatDate(now.getEndDate()));
            autoModifyByDaily(now.getDataType(),DateUtil.formatDate(now.getStartDate()),DateUtil.formatDate(now.getEndDate()));
        }
    }

    @Override
    public void autoModify(String dataType, String startDate, String endDate) {
        incomeMapper.autoModify(dataType,startDate,endDate);
    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {
        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));

        incomeMapper.autoModify(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        incomeMapper.autoModify(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }
}
