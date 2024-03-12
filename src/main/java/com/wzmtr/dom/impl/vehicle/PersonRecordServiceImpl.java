package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.PersonRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.PersonRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.PersonRecordMapper;
import com.wzmtr.dom.service.vehicle.PersonRecordService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-当日人员情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Service
public class PersonRecordServiceImpl implements PersonRecordService {

    @Autowired
    private PersonRecordMapper trainRecordMapper;

    @Override
    public Page<PersonRecordResDTO> page(String startTime, String endTime, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trainRecordMapper.page(pageReqDTO.of(), startTime, endTime);
    }

    @Override
    public PersonRecordResDTO detail(String id) {
        return trainRecordMapper.detail(id);
    }

    @Override
    public PersonRecordResDTO getOcmPersonRecord(String time) {
        // todo 调用 乘务系统接口 根据日期获取当日人员情况
        return new PersonRecordResDTO();
    }

    @Override
    public void add(PersonRecordReqDTO personRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = trainRecordMapper.selectIsExist(personRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期当日人员数据已存在，无法重复新增");
        }
        personRecordReqDTO.setId(TokenUtils.getUuId());
        personRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        trainRecordMapper.add(personRecordReqDTO);
    }

    @Override
    public void modify(PersonRecordReqDTO personRecordReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = trainRecordMapper.selectIsExist(personRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        personRecordReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        trainRecordMapper.modify(personRecordReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            trainRecordMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
