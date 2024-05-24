package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.hotline.HandoverAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.HotLineHandoverMapper;
import com.wzmtr.dom.service.traffic.HotLineHandoverService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 客运部-需转交其他部门做处理事项
 * @author  Ray
 * @version 1.0
 * @date 2024/05/06
 */
@Service
@Slf4j
public class HotLineHandoverServiceImpl implements HotLineHandoverService {
    @Autowired
    private HotLineHandoverMapper hotLineHandoverMapper;

    @Override
    public Page<HotLineHandoverListResDTO> pageRecord(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return hotLineHandoverMapper.pageRecord(pageReqDTO.of(), startDate, endDate,dataType);
    }

    @Override
    public Page<HotLineHandoverDetailResDTO> pageInfo(String id, String date, String dataType, String startDate,
                                                      String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return hotLineHandoverMapper.pageInfo(pageReqDTO.of(), id, date, dataType, startDate, endDate);
    }

    @Override
    public void addRecord(HotLineHandoverAddReqDTO handoverAddReqDTO) {
        Integer result = hotLineHandoverMapper.selectRecordIsExist(handoverAddReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期调度命令记录数据已存在，无法重复新增");
        }
        handoverAddReqDTO.setId(TokenUtils.getUuId());
        handoverAddReqDTO.setHandoverCount(0L);
        handoverAddReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        hotLineHandoverMapper.addRecord(handoverAddReqDTO);

        //周报/月报 新增时，统计数据
        if(!CommonConstants.DATA_TYPE_DAILY.equals(handoverAddReqDTO.getDataType()+"")){
            hotLineHandoverMapper.autoModifyByDaily(handoverAddReqDTO.getDataType()+"",
                    DateUtil.formatDate(handoverAddReqDTO.getStartDate())
                    ,DateUtil.formatDate(handoverAddReqDTO.getEndDate()));
        }
    }

    @Override
    public void addInfo(HandoverAddDataReqDTO handoverAddDataReqDTO) {
        handoverAddDataReqDTO.setId(TokenUtils.getUuId());
        handoverAddDataReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        hotLineHandoverMapper.addInfo(handoverAddDataReqDTO);

        // 修改记录需转交件数
        HotLineHandoverAddReqDTO handoverAddReq = new HotLineHandoverAddReqDTO();
        handoverAddReq.setUpdateBy(TokenUtils.getCurrentPersonId());
        handoverAddReq.setId(handoverAddDataReqDTO.getRecordId());
        hotLineHandoverMapper.modifyRecord(handoverAddReq);

        //更新周报/月报统计数据
        if(CommonConstants.DATA_TYPE_DAILY.equals(handoverAddDataReqDTO.getDataType()+"")){
            autoModifyByDaily(handoverAddDataReqDTO.getDataType()+"",
                    DateUtil.formatDate(handoverAddDataReqDTO.getStartDate())
                    ,DateUtil.formatDate(handoverAddDataReqDTO.getEndDate()));
        }

    }

    @Override
    public void modifyInfo(HandoverAddDataReqDTO handoverAddDataReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = hotLineHandoverMapper.selectInfoIsExist(handoverAddDataReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        handoverAddDataReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        hotLineHandoverMapper.modifyInfo(handoverAddDataReqDTO);
    }

    @Override
    public void deleteRecord(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            hotLineHandoverMapper.deleteRecord(ids, TokenUtils.getCurrentPersonId());
            hotLineHandoverMapper.deleteInfo(ids, null, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void deleteOrder(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            HotLineHandoverDetailResDTO res = hotLineHandoverMapper.getInfoDetail(ids.get(0));
            hotLineHandoverMapper.deleteInfo(null, ids, TokenUtils.getCurrentPersonId());

            HotLineHandoverAddReqDTO req = new HotLineHandoverAddReqDTO();
            req.setId(res.getRecordId());
            req.setUpdateBy(TokenUtils.getCurrentPersonId());
            hotLineHandoverMapper.modifyRecord(req);

            //更新周报/月报统计数据
            if(CommonConstants.DATA_TYPE_DAILY.equals(res.getDataType()+"")){
                autoModifyByDaily(res.getDataType()+"",
                        DateUtil.formatDate(res.getStartDate())
                        ,DateUtil.formatDate(res.getEndDate()));
            }
        }
    }


    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {

        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(endDate));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        hotLineHandoverMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
        hotLineHandoverMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }


}
