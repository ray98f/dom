package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.SecurityReqDTO;
import com.wzmtr.dom.dto.res.vehicle.SecurityResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.SecurityMapper;
import com.wzmtr.dom.service.vehicle.SecurityService;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/15 14:15
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityMapper securityMapper;

    @Override
    public Page<SecurityResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return securityMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public SecurityResDTO detail(String id) {
        return securityMapper.queryInfoById(id);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, SecurityReqDTO securityReqDTO) {
        int existFlag = securityMapper.checkExist(securityReqDTO.getDataType(),
                securityReqDTO.getStartDate(),securityReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(securityReqDTO.getDataType())){

            //日期校验
            if(!securityReqDTO.getStartDate().equals(securityReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            securityReqDTO.setDataDate(securityReqDTO.getStartDate());
        }

        securityReqDTO.setCreateBy(currentLoginUser.getPersonId());
        securityReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        securityReqDTO.setId(TokenUtils.getUuId());
        try{
            securityMapper.add(securityReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, SecurityReqDTO securityReqDTO) {
        securityReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = securityMapper.modify(securityReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }
}
