package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.TrainRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.TrainRecordResDTO;
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
    public Page<TrainRecordResDTO> page(String startTime, String endTime, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trainRecordMapper.page(pageReqDTO.of(), startTime, endTime);
    }

    @Override
    public TrainRecordResDTO detail(String id) {
        return trainRecordMapper.detail(id);
    }

    @Override
    public void add(TrainRecordReqDTO trainRecordReqDTO) {
        Integer result = trainRecordMapper.selectIsExist(trainRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期班组培训数据已存在，无法重复新增");
        }
        trainRecordReqDTO.setId(TokenUtils.getUuId());
        trainRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        trainRecordMapper.add(trainRecordReqDTO);
    }

    @Override
    public void modify(TrainRecordReqDTO trainRecordReqDTO) {
        Integer result = trainRecordMapper.selectIsExist(trainRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已更新，请刷新列表并重新编辑数据");
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
