package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.TrainRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.TrainRecordResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-班组培训情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Mapper
@Repository
public interface TrainRecordMapper {

    /**
     * 分页查询班组培训情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 班组培训情况列表
     */
    Page<TrainRecordResDTO> page(Page<TrainRecordResDTO> page, String startDate, String endDate);

    /**
     * 获取班组培训情况详情
     * @param id id
     * @return 班组培训情况详情
     */
    TrainRecordResDTO detail(String id);

    /**
     * 查询当天班组培训情况是否已存在
     * @param trainRecordReqDTO 班组培训情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(TrainRecordReqDTO trainRecordReqDTO);

    /**
     * 新增班组培训情况
     * @param trainRecordReqDTO 班组培训情况参数
     */
    void add(TrainRecordReqDTO trainRecordReqDTO);

    /**
     * 编辑班组培训情况
     * @param trainRecordReqDTO 班组培训情况参数
     */
    void modify(TrainRecordReqDTO trainRecordReqDTO);

    /**
     * 删除班组培训情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
