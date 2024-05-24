package com.wzmtr.dom.impl.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.ImportantWorkReqDTO;
import com.wzmtr.dom.dto.res.traffic.ImportantWorkResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.ImportantWorkMapper;
import com.wzmtr.dom.service.traffic.ImportantWorkService;
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
public class ImportantWorkServiceImpl implements ImportantWorkService {

    @Autowired
    private ImportantWorkMapper importantWorkMapper;

    @Override
    public Page<ImportantWorkResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return importantWorkMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public ImportantWorkResDTO detail(String recordId) {
        return importantWorkMapper.queryInfoById(recordId);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, ImportantWorkReqDTO importantWorkReqDTO) {
//        if(currentLoginUser.getStationCode() == null){
//            throw new CommonException(ErrorCode.USER_NOT_BIND_STATION);
//        }
        int existFlag = importantWorkMapper.checkExist(importantWorkReqDTO.getDataType(),
                importantWorkReqDTO.getStartDate(),
                importantWorkReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(importantWorkReqDTO.getDataType())){

            //日期校验
            if(!importantWorkReqDTO.getStartDate().equals(importantWorkReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }

            importantWorkReqDTO.setDataDate(importantWorkReqDTO.getStartDate());
        }
        importantWorkReqDTO.setCreateBy(currentLoginUser.getPersonId());
        importantWorkReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        importantWorkReqDTO.setId(TokenUtils.getUuId());
        try{
            importantWorkMapper.add(importantWorkReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, ImportantWorkReqDTO importantWorkReqDTO) {
        importantWorkReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = importantWorkMapper.modify(importantWorkReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }
}
