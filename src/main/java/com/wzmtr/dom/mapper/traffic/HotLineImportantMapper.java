package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:32
 */
@Mapper
@Repository
public interface HotLineImportantMapper extends BaseMapper<TrafficHotlineImportantDO> {
    void insertList(@Param("list") List<TrafficHotlineImportantDO> list);

    Integer selectIsExist(HotLineImportantAddReqDTO req);

    // void updateMany(List<TrafficHotlineImportantDO> list);

    List<HotLineImportantListResDTO> list(@Param("req") HotLineSummaryListReqDTO reqDTO);

    List<HotLineImportantDetailResDTO> selectByDate(String date, String startDate, String endDate, String dataType);

    void showOrHide(String id,String isHide);

    void autoModify(Long count,String hotlineType,String dataType,String startDate, String endDate);

    void autoModifyByDaily(String hotlineType,String dataType,String startDate, String endDate);
}
