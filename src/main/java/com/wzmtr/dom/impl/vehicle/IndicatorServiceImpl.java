package com.wzmtr.dom.impl.vehicle;

import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO) {
        try{
            if(DateUtils.isValidDateFormat(indicatorReqDTO.getDay(), CommonConstants.DATE_FORMAT)){
                indicatorReqDTO.setCreateBy(currentLoginUser.getPersonId());
                indicatorReqDTO.setUpdateBy(currentLoginUser.getPersonId());
                indicatorMapper.add(indicatorReqDTO);
            }else{
                throw new CommonException(ErrorCode.PARAM_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
    }
}
