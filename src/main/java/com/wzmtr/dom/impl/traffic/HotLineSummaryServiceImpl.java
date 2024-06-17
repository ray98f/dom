package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser,HotLineSummaryAddReqDTO reqDTO) {
        try{
            TrafficHotlineSummaryDO trafficHotlineSummaryDO = reqDTO.toDO(reqDTO);
            trafficHotlineSummaryDO.setCreateBy(currentLoginUser.getPersonId());
            trafficHotlineSummaryDO.setId(TokenUtils.getUuId());
            trafficHotlineSummaryDO.setDelFlag("0");
            trafficHotlineSummaryDO.setCreateDate(DateUtils.currentDate());
            trafficHotlineSummaryDO.setCreateBy(currentLoginUser.getPersonId());
            trafficHotlineSummaryDO.setVersion("1");
            Integer result = hotLineSummaryMapper.selectIsExist(trafficHotlineSummaryDO);
            if (result > 0) {
                throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期数据已存在，无法重复新增");
            }
            hotLineSummaryMapper.insert(trafficHotlineSummaryDO);
            // 初始化重要热线内容
            HotLineImportantAddReqDTO req = new HotLineImportantAddReqDTO();
            org.springframework.beans.BeanUtils.copyProperties(reqDTO, req);
            hotLineImportantService.add(currentLoginUser,req);
        }catch (Exception e){
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期数据已存在，无法重复新增");
        }

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
        // 接听总量=咨询+求助+建议+总投诉+热线表演+S1转接+其他
        incomeRecordDO.setAnswerTotal(Optional.ofNullable(incomeRecordDO.getConsult()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getResort()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getSuggest()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getComplaintTotal()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getType1Praise()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getS1Switch()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getOther()).orElse(0L));
        // 总投诉=有责投诉+无责投诉
        incomeRecordDO.setComplaintTotal(Optional.ofNullable(incomeRecordDO.getType1Complaint()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getType2Complaint()).orElse(0L));
        // 总表扬=热线表扬+车站表扬
        incomeRecordDO.setPraiseTotal(Optional.ofNullable(incomeRecordDO.getType1Praise()).orElse(0L)
                + Optional.ofNullable(incomeRecordDO.getType2Praise()).orElse(0L));
        incomeRecordDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        incomeRecordDO.setVersion(String.valueOf(Integer.parseInt(now.getVersion()) + 1));
        hotLineSummaryMapper.updateById(incomeRecordDO);

        //日报数据编辑后，更新周报/月报统计
        if(CommonConstants.DATA_TYPE_DAILY.equals(reqDTO.getDataType())){
            autoModifyByDaily(reqDTO.getDataType(), DateUtil.formatDate(reqDTO.getStartDate()),
                    DateUtil.formatDate(reqDTO.getEndDate()));

            hotLineImportantService.autoModify(reqDTO.getDataType(), DateUtil.formatDate(reqDTO.getStartDate()),
                    DateUtil.formatDate(reqDTO.getEndDate()));

            //hotLineImportantService
            hotLineImportantService.autoModifyByDaily(reqDTO.getDataType(), DateUtil.formatDate(reqDTO.getStartDate()),
                    DateUtil.formatDate(reqDTO.getEndDate()));
        }else{
            hotLineSummaryMapper.autoModifyByDaily(reqDTO.getDataType(), DateUtil.formatDate(reqDTO.getStartDate()),
                    DateUtil.formatDate(reqDTO.getEndDate()));
        }
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


    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {
        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(endDate));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        //更新周报
        hotLineSummaryMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //更新月报
        hotLineSummaryMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }


}
