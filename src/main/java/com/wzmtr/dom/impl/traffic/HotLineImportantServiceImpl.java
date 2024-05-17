package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineSummaryDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryDetailReqDTO;
import com.wzmtr.dom.dto.res.common.DictResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.common.DictMapper;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.mapper.traffic.HotLineSummaryMapper;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import com.wzmtr.dom.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class HotLineImportantServiceImpl implements HotLineImportantService {

    @Autowired
    private HotLineSummaryMapper hotLineSummaryMapper;

    @Autowired
    private HotLineImportantMapper hotLineImportantMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<HotLineImportantDetailResDTO> detail(String date, String startDate, String endDate, String dataType) {
        return hotLineImportantMapper.selectByDate(date, startDate, endDate, dataType);
    }

    @Override
    @Transactional
    public void add(HotLineImportantAddReqDTO req) {
        List<TrafficHotlineImportantDO> list = new ArrayList<>();
        List<DictResDTO> dictList = dictMapper.list(CommonConstants.HOTLINE_TYPE, null, CommonConstants.ZERO_STRING);
        if (StringUtils.isNotEmpty(dictList)) {
            for (DictResDTO dict : dictList) {
                TrafficHotlineImportantDO res = new TrafficHotlineImportantDO();
                res.setId(TokenUtils.getUuId());
                res.setCreateBy(TokenUtils.getCurrentPersonId());
                res.setDataDate(req.getDataDate());
                res.setStartDate(req.getStartDate());
                res.setEndDate(req.getEndDate());
                res.setDataType(req.getDataType());
                res.setHotlineType(dict.getCode());
                list.add(res);
            }
        }
        if (CollectionUtil.isNotEmpty(list)) {
            hotLineImportantMapper.insertList(list);
        }

        //周报月报的情况统计
        if(!(CommonConstants.ONE_STRING).equals(req.getDataType())){

        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineImportantAddReqDTO reqDTO) {
        List<HotLineImportantAddDataReqDTO> addDataReq = Assert.notNull(reqDTO.getHotlineImportantList(), "参数缺失");
        addDataReq.forEach(x -> {
            TrafficHotlineImportantDO convert = BeanUtils.convert(x, TrafficHotlineImportantDO.class);
            convert.setUpdateBy(TokenUtils.getCurrentPersonId());
            convert.setUpdateDate(DateUtils.currentDate());
            hotLineImportantMapper.updateById(convert);
        });
    }


    @Override
    public List<HotLineImportantDetailResDTO> acc(SidReqDTO reqDTO) {
        return null;
    }

    @Override
    public void autoModify(String dataType,String startDate,String endDate){
        HotLineSummaryDetailReqDTO req = new HotLineSummaryDetailReqDTO();
        req.setDataType(dataType);
        req.setStartDate(startDate);
        req.setEndDate(endDate);
        TrafficHotlineSummaryDO summaryDetail = hotLineSummaryMapper.detail(req);

        //投诉 = (汇总)总投诉
        hotLineImportantMapper.autoModify(summaryDetail.getComplaintTotal(),CommonConstants.ONE_STRING,dataType,startDate,endDate);
        //表扬 = (汇总)总表扬 + 锦旗
        hotLineImportantMapper.autoModify(summaryDetail.getPraiseTotal() + summaryDetail.getPennant(),CommonConstants.TWO_STRING,dataType,startDate,endDate);
        //建议 = (汇总)建议
        hotLineImportantMapper.autoModify(summaryDetail.getSuggest(),CommonConstants.THREE_STRING,dataType,startDate,endDate);
        //咨询 = (汇总)咨询
        hotLineImportantMapper.autoModify(summaryDetail.getConsult(),CommonConstants.FOUR_STRING,dataType,startDate,endDate);
        //求助 = (汇总)求助
        hotLineImportantMapper.autoModify(summaryDetail.getResort(),CommonConstants.FIVE_STRING,dataType,startDate,endDate);
        // 转接 = (汇总)S1转接
        hotLineImportantMapper.autoModify(summaryDetail.getS1Switch(),CommonConstants.SIX_STRING,dataType,startDate,endDate);
        //温州轨道APP = (汇总)APP回复
        hotLineImportantMapper.autoModify(summaryDetail.getAppAnswer(),CommonConstants.SEVEN_STRING,dataType,startDate,endDate);
        //其他 = (汇总) 其他
        hotLineImportantMapper.autoModify(summaryDetail.getOther(),CommonConstants.NINE_STRING,dataType,startDate,endDate);

    }

    @Override
    public void autoModifyByDaily(String dataType,String startDate,String endDate){

    }
}
