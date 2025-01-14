package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.vehicle.TrainRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.TrainRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.TrainRecordMapper;
import com.wzmtr.dom.service.vehicle.TrainRecordService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-班组培训情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Service
public class TrainRecordServiceImpl implements TrainRecordService {

    @Autowired
    private TrainRecordMapper trainRecordMapper;

    @Override
    public Page<TrainRecordResDTO> page(String dataType,String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trainRecordMapper.page(pageReqDTO.of(), dataType,startDate, endDate);
    }

    @Override
    public TrainRecordResDTO detail(String id) {
        return trainRecordMapper.detail(id);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser,TrainRecordReqDTO trainRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = trainRecordMapper.selectIsExist(trainRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期班组培训数据已存在，无法重复新增");
        }
        trainRecordReqDTO.setId(TokenUtils.getUuId());
        trainRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        trainRecordMapper.add(trainRecordReqDTO);
    }

    @Override
    public void modify(TrainRecordReqDTO trainRecordReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = trainRecordMapper.selectIsExist(trainRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        trainRecordReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        trainRecordMapper.modify(trainRecordReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            trainRecordMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
