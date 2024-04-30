package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineSummaryDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.mapper.traffic.HotLineSummaryMapper;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import com.wzmtr.dom.service.traffic.HotLineSummaryService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.DateUtils;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class HotLineSummaryServiceImpl implements HotLineSummaryService {
    @Autowired
    private HotLineSummaryMapper hotLineSummaryMapper;
    @Autowired
    private HotLineImportantMapper hotLineImportantMapper;
    @Autowired
    private HotLineImportantService hotLineImportantService;

    @Override
    public HotLineSummaryDetailResDTO detail(HotLineSummaryDetailReqDTO reqDTO) {
        TrafficHotlineSummaryDO trafficHotlineSummaryDO = hotLineSummaryMapper.detail(reqDTO);
        if (StringUtils.isNotNull(trafficHotlineSummaryDO)) {
            return BeanUtils.convert(trafficHotlineSummaryDO, HotLineSummaryDetailResDTO.class);
        }
        return new HotLineSummaryDetailResDTO();
    }

    @Override
    public void add(HotLineSummaryAddReqDTO reqDTO) {
        TrafficHotlineSummaryDO trafficHotlineSummaryDO = reqDTO.toDO(reqDTO);
        trafficHotlineSummaryDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineSummaryDO.setId(TokenUtils.getUuId());
        trafficHotlineSummaryDO.setDelFlag("0");
        trafficHotlineSummaryDO.setCreateDate(DateUtils.currentDate());
        trafficHotlineSummaryDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineSummaryDO.setVersion("1");
        Integer result = hotLineSummaryMapper.selectIsExist(trafficHotlineSummaryDO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期服务热线汇总数据已存在，无法重复新增");
        }
        hotLineSummaryMapper.insert(trafficHotlineSummaryDO);
        // 初始化重要热线内容
        HotLineImportantAddReqDTO req = new HotLineImportantAddReqDTO();
        org.springframework.beans.BeanUtils.copyProperties(reqDTO, req);
        hotLineImportantService.add(req);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineSummaryAddReqDTO reqDTO) {
        String id = Assert.notNull(reqDTO.getId(), "ID不能为空");
        TrafficHotlineSummaryDO now = hotLineSummaryMapper.selectById(id);
        TrafficHotlineSummaryDO incomeRecordDO = reqDTO.toDO(reqDTO);
        // 判断修改数据版本号是否一致
        Integer result = hotLineSummaryMapper.selectIsExist(incomeRecordDO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        incomeRecordDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        incomeRecordDO.setVersion(String.valueOf(Integer.parseInt(now.getVersion()) + 1));
        hotLineSummaryMapper.updateById(incomeRecordDO);
    }

    @Override
    public Page<HotLineSummaryListResDTO> list(HotLineSummaryListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<HotLineSummaryListResDTO> list = hotLineSummaryMapper.list(reqDTO.of(), reqDTO);
        List<HotLineSummaryListResDTO> records = list.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        records.forEach(a -> {
            HotLineSummaryListReqDTO req = new HotLineSummaryListReqDTO();
            req.setStartDate(DateUtils.dateTime(a.getStartDate()));
            req.setEndDate(DateUtils.dateTime(a.getEndDate()));
            req.setDataType(a.getDataType());
            List<HotLineImportantListResDTO> list1 = hotLineImportantMapper.list(req);
            //查日期内是否存在重要内容数据
            if (CollectionUtil.isNotEmpty(list1)) {
                a.setHotLineImportant(list1);
            }
        });
        return list;
    }

    @Override
    public HotLineSummaryDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }


}
