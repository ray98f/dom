package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.common.OpenDispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchRecordReqDTO;
import com.wzmtr.dom.dto.res.common.OpenDispatchOrderRes;
import com.wzmtr.dom.dto.res.vehicle.DispatchOrderResDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.DispatchOrderStatusEnum;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DispatchMapper;
import com.wzmtr.dom.service.common.ThirdService;
import com.wzmtr.dom.service.vehicle.DispatchService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 车辆部-调度命令管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private ThirdService thirdService;

    @Autowired
    private DispatchMapper dispatchMapper;

    @Override
    public Page<DispatchRecordResDTO> pageRecord(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dispatchMapper.pageRecord(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public Page<DispatchOrderResDTO> pageOrder(String recordId, String dataType, PageReqDTO pageReqDTO) {
        DispatchRecordResDTO res = dispatchMapper.getRecordDetail(recordId,null,null,null);

        return dispatchMapper.pageOrder(pageReqDTO.of(), CommonConstants.DATA_TYPE_DAILY,
                DateUtil.formatDate(res.getStartDate()), DateUtil.formatDate(res.getEndDate()));
    }

    @Override
    public List<DispatchOrderResDTO> orderList(String recordId, String dataType, String startDate, String endDate) {
        return dispatchMapper.orderList(recordId, dataType, startDate, endDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DispatchOrderResDTO> getCsmDispatch(String recordId, String dataType,String startDate,String endDate) {
        try {
            if(CommonConstants.DATA_TYPE_DAILY.equals(dataType)){
                Date date = DateUtil.parse(endDate);
                String endDateNext = DateUtil.formatDate(DateUtil.offsetDay(date, 1)) + CommonConstants.SYNC_DATA_TIME;
                OpenDispatchOrderReqDTO req = OpenDispatchOrderReqDTO.builder()
                        .startTime(startDate + CommonConstants.SYNC_DATA_TIME)
                        .endTime(endDateNext)
                        .dispatchorderStatus(DispatchOrderStatusEnum.RELEASED.value())
                        .build();
                List<OpenDispatchOrderRes> res = thirdService.getCsmDispatchOrder(req);

                if(res != null && res.size() > 0){

                    //删除后，再插入
                    dispatchMapper.deleteOrder(dataType,startDate ,endDate);
                    List<DispatchOrderReqDTO> addList = res.stream().map(this::dispatchOrderCopy).collect(Collectors.toList());
                    if(addList.size()>0){
                        addOrder(recordId,TokenUtils.getCurrentPersonId(), startDate, endDate,addList);
                    }
                }
                autoModifyByDaily(dataType,startDate,endDate);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.NORMAL_ERROR, "同步失败");
        }
        return null;
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

        autoModify(dispatchRecordReqDTO.getDataType(),DateUtil.formatDate(dispatchRecordReqDTO.getStartDate())
                ,DateUtil.formatDate(dispatchRecordReqDTO.getEndDate()));
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

    @Override
    public void autoModify(String dataType, String startDate, String endDate) {
        dispatchMapper.autoModify(dataType,startDate,endDate);
    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {

        dispatchMapper.autoModify(dataType,startDate,endDate);

        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));
        dispatchMapper.autoModify(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));
        dispatchMapper.autoModify(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }

    private void addOrder(String recordId,String createBy,String startDate,String endDate,List<DispatchOrderReqDTO> list){
        dispatchMapper.addOrderBatch(recordId,createBy,startDate,endDate,list);
    }

    private DispatchOrderReqDTO dispatchOrderCopy(OpenDispatchOrderRes res){
        DispatchOrderReqDTO dto = new DispatchOrderReqDTO();
        dto.setId(res.getDispatchorderId());
        dto.setOrderCode(res.getOrderCode());
        dto.setOrderDesc(res.getDispatchorderDesc());
        dto.setOrderTime(DateUtil.parseDate(res.getOrderTime()));

        return dto;
    }

}
