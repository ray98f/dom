package com.wzmtr.dom.impl.operate;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowDetailResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerInfoResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperatePassengerFlowDetailMapper;
import com.wzmtr.dom.mapper.operate.OperatePassengerFlowInfoMapper;
import com.wzmtr.dom.service.operate.OperatePassengerFlowService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.HttpUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 17:02
 */
@Service
public class OperatePassengerFlowServiceImpl implements OperatePassengerFlowService {
    @Autowired
    private OperatePassengerFlowDetailMapper passengerFlowDetailMapper;

    @Autowired
    private OperatePassengerFlowInfoMapper operatePassengerFlowInfoMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Value("${open-api.acc.api-app-key}")
    private String apiAppKey;

    @Value("${open-api.acc.person-count}")
    private String accPersonCountApi;


    @Override
    public Page<PassengerFlowListResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return passengerFlowDetailMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
    }

    @Override
    public PassengerFlowDetailResDTO detail(String id) {
        PassengerFlowDetailResDTO passengerFlowDetailResDTO = new PassengerFlowDetailResDTO();
        // 获取详情
        OperatePassengerFlowDetailDO detail = passengerFlowDetailMapper.selectById(id);
        if (null != detail) {
            passengerFlowDetailResDTO = BeanUtils.convert(detail, PassengerFlowDetailResDTO.class);
            // 车站客流列表
            List<PassengerInfoResDTO> passengerInfoResDTOS = operatePassengerFlowInfoMapper.eachStation(DateUtil.formatDate(detail.getStartDate()),
                    DateUtil.formatDate(detail.getEndDate()));
            passengerFlowDetailResDTO.setStationPassengerList(passengerInfoResDTOS);
        }
        return passengerFlowDetailResDTO;
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, PassengerFlowAddReqDTO addReqDTO) {
        // 日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(addReqDTO.getDataType())) {
            // 日期校验
            if (!addReqDTO.getStartDate().equals(addReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            addReqDTO.setDataDate(addReqDTO.getStartDate());
        }
        addReqDTO.setCreateBy(currentLoginUser.getPersonId());
        addReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        addReqDTO.setId(TokenUtils.getUuId());
        passengerFlowDetailMapper.add(addReqDTO);
        // 日报类型同步客流数据 更新客流数据
        if (CommonConstants.DATA_TYPE_DAILY.equals(addReqDTO.getDataType())) {
            syncACCdata(addReqDTO);
            passengerFlowDetailMapper.modifyCount(addReqDTO.getId(), addReqDTO.getStartDate(), addReqDTO.getEndDate());
            // 周报、月报类型 更新本周 本月客流数据
        } else {
            passengerFlowDetailMapper.modifyCount(addReqDTO.getId(), addReqDTO.getStartDate(), addReqDTO.getEndDate());
        }
    }

    private void syncACCdata(PassengerFlowAddReqDTO addReqDTO) {
        HashMap<String, Object> res = JSONObject.parseObject(HttpUtils.doGet(
                accPersonCountApi + "?businessDay=" + addReqDTO.getStartDate(), null, apiAppKey), HashMap.class);
        if (CollectionUtil.isEmpty(res)){
            return;
        }
        JSONArray jsonArray = JSONArray.parseArray(String.valueOf(res.get("data")));
        List<HashMap> personCountList = JSONArray.parseArray(jsonArray.toJSONString(), HashMap.class);
        List<PassengerInfoReqDTO> infoReqDTOList = new ArrayList<>();
        if (personCountList != null && personCountList.size() > 0) {

            // 本日S2线客流
            Integer dayPersonCount = 0;
            for (HashMap<String, Object> item : personCountList) {

                // S2线
                if (CommonConstants.TWO_STRING.equals(item.get("LINE_ID").toString())) {
                    PassengerInfoReqDTO infoReqDTO = new PassengerInfoReqDTO();
                    infoReqDTO.setId(TokenUtils.getUuId());
                    infoReqDTO.setRecordId(addReqDTO.getId());
                    infoReqDTO.setDataType(addReqDTO.getDataType());
                    infoReqDTO.setDataDate(addReqDTO.getStartDate());
                    infoReqDTO.setStartDate(addReqDTO.getStartDate());
                    infoReqDTO.setEndDate(addReqDTO.getEndDate());
                    infoReqDTO.setCreateBy(addReqDTO.getCreateBy());
                    infoReqDTO.setUpdateBy(addReqDTO.getUpdateBy());
                    infoReqDTO.setStationCode(HexUtil.toHex(Integer.parseInt(item.get("STATION_ID").toString())));
                    int totalIn = Integer.parseInt(item.get("TOTAL_IN").toString());
                    dayPersonCount += totalIn;

                    // 换算成万人
                    Double passenger = new Double(Math.round(totalIn * 10000 / CommonConstants.TEN_THOUSAND) / CommonConstants.TEN_THOUSAND_DOUBLE);
                    infoReqDTO.setPassenger(passenger);
                    infoReqDTOList.add(infoReqDTO);
                }
            }
            // 新增
            if (infoReqDTOList != null && infoReqDTOList.size() > 0) {
                doCreatePassengerBatch(infoReqDTOList);
            }

        }
    }


    @Override
    public void modifyStationPassenger(PassengerInfoReqDTO req) {
        req.setUpdateBy(TokenUtils.getCurrentPersonId());
        try {
            int res = operatePassengerFlowInfoMapper.modifyStationPassenger(req);
            if (res <= 0) {
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        } catch (Exception e) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 新增车站客流
     */
    private void doCreatePassengerBatch(List<PassengerInfoReqDTO> infoReqDTOList) {

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            OperatePassengerFlowInfoMapper mapper = sqlSession.getMapper(OperatePassengerFlowInfoMapper.class);
            for (PassengerInfoReqDTO item : infoReqDTOList) {
                mapper.createStationPassenger(item);
            }
            sqlSession.commit();
            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.clearCache();
            sqlSession.close();
            batchResults.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void modify(PassengerFlowAddReqDTO req) {
        req.setUpdateBy(TokenUtils.getCurrentPersonId());
        passengerFlowDetailMapper.modify(req);
    }
}
