package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.vehicle.PersonRecordReqDTO;
import com.wzmtr.dom.dto.res.OpenDriverInfoRes;
import com.wzmtr.dom.dto.res.vehicle.PersonRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.PersonRecordMapper;
import com.wzmtr.dom.service.common.ThirdService;
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
    private ThirdService thirdService;

    @Autowired
    private PersonRecordMapper trainRecordMapper;

    @Override
    public Page<PersonRecordResDTO> page(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trainRecordMapper.page(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public PersonRecordResDTO detail(String id) {
        return trainRecordMapper.detail(id);
    }

    @Override
    public PersonRecordResDTO syncData(String date) {
        OpenDriverInfoRes res = thirdService.getDriverInfo(date);
        PersonRecordReqDTO personRecordReqDTO = PersonRecordReqDTO.builder()
                .mainDriverDay(res.getDayHeadDriver())
                .mainDriverNight(res.getNightHeadDriver())
                .guideDriverDay(res.getDayGuideDriver())
                .guideDriverNight(res.getNightGuideDriver())
                .leavePersonal(res.getPersonalLeave())
                .leaveSick(res.getSickLeave())
                .leaveAnnual(res.getAnnualLeave())
                .leaveOther(res.getOtherLeave())
                .dataDate(DateUtil.parseDate(date))
                .build();
        trainRecordMapper.modifyBySync(personRecordReqDTO);
        return new PersonRecordResDTO();
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser,PersonRecordReqDTO personRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = trainRecordMapper.selectIsExist(personRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期当日人员数据已存在，无法重复新增");
        }
        personRecordReqDTO.setId(TokenUtils.getUuId());
        personRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
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
