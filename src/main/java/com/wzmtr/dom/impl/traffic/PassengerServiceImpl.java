package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.PassengerMapper;
import com.wzmtr.dom.service.traffic.PassengerService;
import com.wzmtr.dom.utils.HttpUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 客运部-客流总体情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 14:45
 */
@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {


    @Value("${open-api.acc.api-app-key}")
    private String apiAppKey;

    @Value("${open-api.acc.person-count}")
    private String accPersonCountApi;

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Page<PassengerResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return passengerMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public PassengerDetailResDTO detail(String recordId) {

        //获取详情
        PassengerDetailResDTO detail = passengerMapper.queryInfoById(recordId);

        //车站情况
        List<PassengerInfoResDTO> stationPassengerList = passengerMapper.stationPassenger( DateUtil.formatDate(detail.getStartDate()),
                DateUtil.formatDate(detail.getEndDate()));
        detail.setStationPassengerList(stationPassengerList);
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, PassengerRecordReqDTO passengerRecordReqDTO) {
        int existFlag = passengerMapper.checkExist(passengerRecordReqDTO.getDataType(),
                passengerRecordReqDTO.getStartDate(),passengerRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(passengerRecordReqDTO.getDataType())){

            //日期校验
            if(!passengerRecordReqDTO.getStartDate().equals(passengerRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            passengerRecordReqDTO.setDataDate(passengerRecordReqDTO.getStartDate());
        }
        passengerRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        passengerRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        passengerRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            passengerMapper.add(passengerRecordReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //日报类型同步客流数据 更新客流数据
        if(CommonConstants.DATA_TYPE_DAILY.equals(passengerRecordReqDTO.getDataType())){
            try{
                //TODO 调试时暂时注释
                //syncACCdata(passengerRecordReqDTO);
                //passengerMapper.modifyCount(passengerRecordReqDTO.getId(),passengerRecordReqDTO.getStartDate(),passengerRecordReqDTO.getEndDate());
            }catch (Exception e){
                throw new CommonException(ErrorCode.INSERT_ERROR);
            }
        // 周报、月报类型 更新本周 本月客流数据
        }else{
            passengerMapper.modifyCount(passengerRecordReqDTO.getId(),passengerRecordReqDTO.getStartDate(),passengerRecordReqDTO.getEndDate());
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, PassengerRecordReqDTO passengerRecordReqDTO) {
        passengerRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = passengerMapper.modify(passengerRecordReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void modifyStationPassenger(CurrentLoginUser currentLoginUser, PassengerInfoReqDTO passengerInfoReqDTO) {
        passengerInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = passengerMapper.modifyStationPassenger(passengerInfoReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 同步ACC客流
     * */
    private void syncACCdata(PassengerRecordReqDTO passengerRecordReqDTO){
        HashMap<String,Object> res = JSONObject.parseObject(HttpUtils.doGet(
                accPersonCountApi+"?businessDay="+passengerRecordReqDTO.getStartDate(), null,apiAppKey), HashMap.class);
        JSONArray jsonArray = JSONArray.parseArray(String.valueOf(res.get("data")));
        List<HashMap> personCountList = JSONArray.parseArray(jsonArray.toJSONString(), HashMap.class);
        List<PassengerInfoReqDTO> infoReqDTOList = new ArrayList<>();
        if(personCountList != null && personCountList.size() > 0){

            //本日S2线客流
            Integer dayPersonCount = 0;
            for(HashMap<String,Object> item:personCountList){

                //S2线
                if(CommonConstants.TWO_STRING.equals(item.get("LINE_ID").toString())){
                    PassengerInfoReqDTO infoReqDTO = new PassengerInfoReqDTO();
                    infoReqDTO.setId(TokenUtils.getUuId());
                    infoReqDTO.setRecordId(passengerRecordReqDTO.getId());
                    infoReqDTO.setDataType(passengerRecordReqDTO.getDataType());
                    infoReqDTO.setDataDate(passengerRecordReqDTO.getStartDate());
                    infoReqDTO.setStartDate(passengerRecordReqDTO.getStartDate());
                    infoReqDTO.setEndDate(passengerRecordReqDTO.getEndDate());
                    infoReqDTO.setCreateBy(passengerRecordReqDTO.getCreateBy());
                    infoReqDTO.setUpdateBy(passengerRecordReqDTO.getUpdateBy());
                    infoReqDTO.setStationCode(HexUtil.toHex(Integer.parseInt(item.get("STATION_ID").toString())));
                    int totalIn = Integer.parseInt(item.get("TOTAL_IN").toString());
                    dayPersonCount += totalIn;

                    //换算成万人
                    Double passenger = new Double(Math.round(totalIn*10000/CommonConstants.TEN_THOUSAND)/CommonConstants.TEN_THOUSAND_DOUBLE);
                    infoReqDTO.setPassenger(passenger);
                    infoReqDTOList.add(infoReqDTO);
                }
            }



            //新增
            if(infoReqDTOList != null && infoReqDTOList.size() > 0){
                doCreatePassengerBatch(infoReqDTOList);
            }

        }
    }

    /**
     * 新增车站客流
     * */
    private  void doCreatePassengerBatch(List<PassengerInfoReqDTO> infoReqDTOList){

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        try{
            PassengerMapper mapper = sqlSession.getMapper(PassengerMapper.class);
            for(PassengerInfoReqDTO item : infoReqDTOList){
                mapper.createStationPassenger(item);
            }
            sqlSession.commit();
            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.clearCache();
            sqlSession.close();
            batchResults.clear();
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }

    private void updateRecordCount(PassengerRecordReqDTO passengerRecordReqDTO){
        //TODO
    }

}
