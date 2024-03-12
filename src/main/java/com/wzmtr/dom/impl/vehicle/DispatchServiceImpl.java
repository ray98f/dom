package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchOrderResDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DispatchMapper;
import com.wzmtr.dom.service.vehicle.DispatchService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆部-调度命令管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchMapper dispatchMapper;

    @Override
    public Page<DispatchRecordResDTO> pageRecord(String startTime, String endTime, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dispatchMapper.pageRecord(pageReqDTO.of(), startTime, endTime);
    }

    @Override
    public List<DispatchOrderResDTO> listOrder(String recordId) {
        return dispatchMapper.listOrder(recordId);
    }

    @Override
    public List<DispatchOrderResDTO> getCsmDispatch(String time) {
        // todo 调用csm系统接口 根据日期获取调度命令数据
        return new ArrayList<>();
    }

    @Override
    public void add(DispatchReqDTO dispatchReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = dispatchMapper.selectRecordIsExist(dispatchReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期调度命令数据已存在，无法重复新增");
        }
        String recordId = TokenUtils.getUuId();
        for (DispatchOrderReqDTO order : dispatchReqDTO.getOrderList()) {
            order.setId(TokenUtils.getUuId());
            order.setRecordId(recordId);
            order.setCreateBy(TokenUtils.getCurrentPersonId());
            dispatchMapper.addOrder(order);
        }
        dispatchReqDTO.getRecord().setId(recordId);
        dispatchReqDTO.getRecord().setOrderNum(dispatchReqDTO.getOrderList().size());
        dispatchReqDTO.getRecord().setCreateBy(TokenUtils.getCurrentPersonId());
        dispatchMapper.addRecord(dispatchReqDTO.getRecord());
    }

    @Override
    public void modify(DispatchReqDTO dispatchReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = dispatchMapper.selectRecordIsExist(dispatchReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        for (DispatchOrderReqDTO order : dispatchReqDTO.getOrderList()) {
            order.setUpdateBy(TokenUtils.getCurrentPersonId());
            dispatchMapper.modifyOrder(order);
        }
        dispatchReqDTO.getRecord().setOrderNum(dispatchReqDTO.getOrderList().size());
        dispatchReqDTO.getRecord().setUpdateBy(TokenUtils.getCurrentPersonId());
        dispatchMapper.modifyRecord(dispatchReqDTO.getRecord());
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dispatchMapper.deleteRecord(ids, TokenUtils.getCurrentPersonId());
            dispatchMapper.deleteOrder(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
