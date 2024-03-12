package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.DateUtils;
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
    public Page<IndicatorResDTO> list(String startDate,String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return indicatorMapper.list(pageReqDTO.of(),startDate,endDate);
    }

    @Override
    public IndicatorResDTO detail(String id) {
        return indicatorMapper.queryInfoById(id);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO) {

        //只添加今天之前的数据
        if(!(DateUtils.isValidDateFormat(indicatorReqDTO.getDay(), CommonConstants.DATE_FORMAT) &&
                DateUtil.compare( new Date(),DateUtil.parseDate(indicatorReqDTO.getDay()),CommonConstants.DATE_FORMAT) > 0) ){
            throw new CommonException(ErrorCode.DATE_ERROR);
        }
        try{
            indicatorReqDTO.setCreateBy(currentLoginUser.getPersonId());
            indicatorReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            indicatorMapper.add(indicatorReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO) {
        indicatorReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = indicatorMapper.modify(indicatorReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            indicatorMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }
}
