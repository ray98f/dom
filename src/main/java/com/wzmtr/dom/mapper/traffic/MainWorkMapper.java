package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.MainWorkReqDTO;
import com.wzmtr.dom.dto.res.traffic.MainWorkResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 13:51
 */
@Mapper
@Repository
public interface MainWorkMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<MainWorkResDTO> list(Page<MainWorkResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return IndicatorResDTO
     */
    int checkExist(String dataType,String startDate,String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return ImportantWorkResDTO
     */
    MainWorkResDTO queryInfoById(String id, String startDate, String endDate);

    /**
     * 新增
     * @param mainWorkReqDTO 入参数
     */
    void add(MainWorkReqDTO mainWorkReqDTO);

    /**
     * 编辑
     * @param mainWorkReqDTO 入参数
     */
    int modify(MainWorkReqDTO mainWorkReqDTO);

}
