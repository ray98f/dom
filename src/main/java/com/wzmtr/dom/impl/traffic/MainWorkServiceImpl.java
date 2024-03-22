package com.wzmtr.dom.impl.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.MainWorkReqDTO;
import com.wzmtr.dom.dto.res.traffic.MainWorkResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.MainWorkMapper;
import com.wzmtr.dom.service.traffic.MainWorkService;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 13:47
 */
@Service
public class MainWorkServiceImpl implements MainWorkService {

    @Autowired
    private MainWorkMapper mainWorkMapper;

    @Override
    public Page<MainWorkResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return mainWorkMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public MainWorkResDTO detail(String recordId) {
        return mainWorkMapper.queryInfoById(recordId);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, MainWorkReqDTO mainWorkReqDTO) {
        if(currentLoginUser.getStationCode() == null){
            throw new CommonException(ErrorCode.USER_NOT_BIND_STATION);
        }
        int existFlag = mainWorkMapper.checkExist(mainWorkReqDTO.getDataType(),
                mainWorkReqDTO.getStartDate(),
                mainWorkReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(mainWorkReqDTO.getDataType())){

            //日期校验
            if(!mainWorkReqDTO.getStartDate().equals(mainWorkReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }

            mainWorkReqDTO.setDataDate(mainWorkReqDTO.getStartDate());
        }
        mainWorkReqDTO.setCreateBy(currentLoginUser.getPersonId());
        mainWorkReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        mainWorkReqDTO.setId(TokenUtils.getUuId());
        try{
            mainWorkMapper.add(mainWorkReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, MainWorkReqDTO mainWorkReqDTO) {
        mainWorkReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = mainWorkMapper.modify(mainWorkReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }
}
