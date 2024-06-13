package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:24
 */
@Service
public class IndicatorServiceImpl implements IndicatorService {

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public Page<IndicatorResDTO> list(String dataType,String startDate,String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return indicatorMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public IndicatorResDTO detail(String id) {
        return indicatorMapper.queryInfoById(id);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO) {
        int existFlag = indicatorMapper.checkExist(indicatorReqDTO.getDataType(),
                indicatorReqDTO.getStartDate(),indicatorReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(indicatorReqDTO.getDataType())){

            //日期校验
            if(!indicatorReqDTO.getStartDate().equals(indicatorReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            indicatorReqDTO.setDataDate(indicatorReqDTO.getStartDate());
        }

        indicatorReqDTO.setCreateBy(currentLoginUser.getPersonId());
        indicatorReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        indicatorReqDTO.setId(TokenUtils.getUuId());
        try{
            indicatorMapper.add(indicatorReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO) {
        IndicatorResDTO indicator = indicatorMapper.queryInfoById(indicatorReqDTO.getId());
        indicatorReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = indicatorMapper.modify(indicatorReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //日报情况下，更新周报/月报统计数据
        if(CommonConstants.ONE_STRING.equals(indicator.getDataType())){
            Date dataDate = indicator.getDataDate();
            Date monday = DateUtil.beginOfWeek(dataDate);
            Date sunday = DateUtil.endOfWeek(dataDate);
            Date monthStart = DateUtil.beginOfMonth(dataDate);
            Date monthEnd = DateUtil.endOfMonth(dataDate);
            //更新周报、月报统计数据
            indicatorMapper.modifyCount2(DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
            indicatorMapper.modifyCount2(DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
        }
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            indicatorMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void autoModify(String dataType, String startDate, String endDate) {

    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {

        indicatorMapper.autoModifyByDaily(dataType,startDate,endDate);

        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));

        indicatorMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        indicatorMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }

    /**
     * 周报、月报更新统计数据
     */
    private void updateRecordCount(String id,String startDate,String endDate){
        indicatorMapper.modifyCount(id,startDate,endDate);
    }
}
