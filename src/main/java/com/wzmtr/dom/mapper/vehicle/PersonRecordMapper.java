package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.PersonRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.PersonRecordResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-当日人员情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Mapper
@Repository
public interface PersonRecordMapper {

    /**
     * 分页查询当日人员情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 当日人员情况列表
     */
    Page<PersonRecordResDTO> page(Page<PersonRecordResDTO> page, String startDate, String endDate);

    /**
     * 获取当日人员情况详情
     * @param id id
     * @return 当日人员情况详情
     */
    PersonRecordResDTO detail(String id);

    /**
     * 查询当天当日人员情况是否已存在
     * @param personRecordReqDTO 当日人员情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(PersonRecordReqDTO personRecordReqDTO);

    /**
     * 新增当日人员情况
     * @param personRecordReqDTO 当日人员情况参数
     */
    void add(PersonRecordReqDTO personRecordReqDTO);

    /**
     * 编辑当日人员情况
     * @param personRecordReqDTO 当日人员情况参数
     */
    void modify(PersonRecordReqDTO personRecordReqDTO);

    /**
     * 删除当日人员情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
