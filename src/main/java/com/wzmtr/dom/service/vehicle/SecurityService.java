package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.req.vehicle.SecurityReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.dto.res.vehicle.SecurityResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-安全运营
 *
 * @author zhangxin
 * @version 1.0O
 * @date 2024/3/8 16:22
 */
public interface SecurityService {


    /**
     * 安全运营-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 重要指标列表
     */
    Page<SecurityResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 安全运营-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    SecurityResDTO detail(String id);

    /**
     * 安全运营-新增
     * @param currentLoginUser 入参数
     * @param securityReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, SecurityReqDTO securityReqDTO);

    /**
     * 安全运营-编辑
     * @param currentLoginUser 入参数
     * @param securityReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, SecurityReqDTO securityReqDTO);

}
