package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.OtherRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.OtherRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-其他情况说明
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
public interface OtherRecordService {

    /**
     * 分页查询其他情况说明列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 其他情况说明列表
     */
    Page<OtherRecordResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO);

    /**
     * 获取其他情况说明详情
     * @param id id
     * @return 其他情况说明详情
     */
    OtherRecordResDTO detail(String id);

    /**
     * 新增其他情况说明
     * @param otherRecordReqDTO 其他情况说明参数
     */
    void add(CurrentLoginUser currentLoginUser,OtherRecordReqDTO otherRecordReqDTO);

    /**
     * 编辑其他情况说明
     * @param otherRecordReqDTO 其他情况说明参数
     */
    void modify(OtherRecordReqDTO otherRecordReqDTO);

    /**
     * 删除其他情况说明
     * @param ids ids
     */
    void delete(List<String> ids);
}
