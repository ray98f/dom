package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchRecordReqDTO;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Page<DispatchRecordResDTO> pageRecord(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dispatchMapper.pageRecord(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public Page<DispatchOrderResDTO> pageOrder(String recordId, String dataType, PageReqDTO pageReqDTO) {
        if (CommonConstants.ONE_STRING.equals(dataType)) {
            return dispatchMapper.pageOrder(pageReqDTO.of(), recordId, null, null);
        } else {
            DispatchRecordResDTO res = dispatchMapper.getRecordDetail(recordId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!Objects.isNull(res)) {
                return dispatchMapper.pageOrder(pageReqDTO.of(), null, sdf.format(res.getStartDate()), sdf.format(res.getEndDate()));
            }
        }
        return new Page<>();
    }

    @Override
    public List<DispatchOrderResDTO> getCsmDispatch(String time) {
        // todo 调用 施工调度系统接口 根据日期获取调度命令数据并新增调度命令详情，修改调度命令记录命令数
        return new ArrayList<>();
    }

    @Override
    public void addRecord(DispatchRecordReqDTO dispatchRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = dispatchMapper.selectRecordIsExist(dispatchRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期调度命令记录数据已存在，无法重复新增");
        }
        dispatchRecordReqDTO.setId(TokenUtils.getUuId());
        dispatchRecordReqDTO.setOrderNum(0);
        dispatchRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        dispatchMapper.addRecord(dispatchRecordReqDTO);
    }

    @Override
    public void modifyOrder(DispatchOrderReqDTO dispatchOrderReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = dispatchMapper.selectOrderIsExist(dispatchOrderReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        dispatchOrderReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        dispatchMapper.modifyOrder(dispatchOrderReqDTO);
    }

    @Override
    public void deleteRecord(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dispatchMapper.deleteRecord(ids, TokenUtils.getCurrentPersonId());
            dispatchMapper.deleteOrder(ids, null, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void deleteOrder(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dispatchMapper.deleteOrder(null, ids, TokenUtils.getCurrentPersonId());
            DispatchOrderResDTO res = dispatchMapper.getOrderDetail(ids.get(0));
            DispatchRecordReqDTO req = new DispatchRecordReqDTO();
            req.setId(res.getRecordId());
            req.setVersion(res.getVersion());
            req.setUpdateBy(TokenUtils.getCurrentPersonId());
            dispatchMapper.modifyRecord(req);
        }
    }

}
