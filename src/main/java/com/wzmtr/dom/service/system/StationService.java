package com.wzmtr.dom.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.StationRoleReqDTO;
import com.wzmtr.dom.dto.res.system.AllStationResDTO;
import com.wzmtr.dom.dto.res.system.StationResDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 系统参数-审核站权限管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
public interface StationService {

    /**
     * 车站列表
     * @param lineCode 所属线路
     * @return 车站
     */
    AllStationResDTO allList(String lineCode);


}
