package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.PersonRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.PersonRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-当日人员情况管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
public interface PersonRecordService {

    /**
     * 分页查询当日人员情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 当日人员情况列表
     */
    Page<PersonRecordResDTO> page(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取当日人员情况详情
     * @param id id
     * @return 当日人员情况详情
     */
    PersonRecordResDTO detail(String id);

    /**
     * 提取乘务系统当日人员情况
     * @param time 日期
     * @return 当日人员情况
     */
    PersonRecordResDTO getOcmPersonRecord(String time);

    /**
     * 新增当日人员情况
     * @param personRecordReqDTO 当日人员情况参数
     */
    void add(CurrentLoginUser currentLoginUser,PersonRecordReqDTO personRecordReqDTO);

    /**
     * 编辑当日人员情况
     * @param personRecordReqDTO 当日人员情况参数
     */
    void modify(PersonRecordReqDTO personRecordReqDTO);

    /**
     * 删除当日人员情况
     * @param ids ids
     */
    void delete(List<String> ids);
}
