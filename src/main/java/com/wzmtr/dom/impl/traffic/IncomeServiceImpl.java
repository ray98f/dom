package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
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
    public void add(IncomeAddReqDTO reqDTO) {
        Integer result = incomeMapper.checkExist(reqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期收益总体情况数据已存在，无法重复新增");
        }
        reqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        reqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        reqDTO.setId(TokenUtils.getUuId());
        incomeMapper.add(reqDTO);
    }


    @Override
    public void modify(CurrentLoginUser currentLoginUser, IncomeAddReqDTO reqDTO) {
        String id = Assert.notNull(reqDTO.getId(), "id不能为空");
        IncomeRecordDO now = incomeMapper.selectById(id);
        IncomeRecordDO incomeRecordDO = reqDTO.toDO(reqDTO);
        incomeRecordDO.setUpdateBy(currentLoginUser.getPersonId());
        incomeRecordDO.setVersion(String.valueOf(Integer.parseInt(now.getVersion()) + 1));
        incomeMapper.updateById(incomeRecordDO);
    }
}
