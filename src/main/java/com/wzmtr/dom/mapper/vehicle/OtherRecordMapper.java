package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.OtherRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.OtherRecordResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-其他情况说明
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Mapper
@Repository
public interface OtherRecordMapper {

    /**
     * 分页查询其他情况说明列表
     * @param page 分页参数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 其他情况说明列表
     */
    Page<OtherRecordResDTO> page(Page<OtherRecordResDTO> page, String startTime, String endTime, String dataType);

    /**
     * 获取其他情况说明详情
     * @param id id
     * @return 其他情况说明详情
     */
    OtherRecordResDTO detail(String id);

    /**
     * 查询当天其他情况说明是否已存在
     * @param otherRecordReqDTO 其他情况说明参数
     * @return 是否已存在
     */
    Integer selectIsExist(OtherRecordReqDTO otherRecordReqDTO);

    /**
     * 新增其他情况说明
     * @param otherRecordReqDTO 其他情况说明参数
     */
    void add(OtherRecordReqDTO otherRecordReqDTO);

    /**
     * 编辑其他情况说明
     * @param otherRecordReqDTO 其他情况说明参数
     */
    void modify(OtherRecordReqDTO otherRecordReqDTO);

    /**
     * 删除其他情况说明
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
