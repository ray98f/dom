package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.req.vehicle.SecurityReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.dto.res.vehicle.SecurityResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-安全运营
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface SecurityMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<SecurityResDTO> list(Page<SecurityResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return SecurityResDTO
     */
    int checkExist(String dataType,String startDate,String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    SecurityResDTO queryInfoById(String id);

    /**
     * 新增
     * @param securityReqDTO 入参数
     */
    void add(SecurityReqDTO securityReqDTO);

    /**
     * 编辑
     * @param securityReqDTO 入参数
     */
    int modify(SecurityReqDTO securityReqDTO);

}
