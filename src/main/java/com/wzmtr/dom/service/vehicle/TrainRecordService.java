package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.TrainRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.TrainRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-班组培训情况管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
public interface TrainRecordService {

    /**
     * 分页查询班组培训情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 班组培训情况列表
     */
    Page<TrainRecordResDTO> page(String dataType,String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取班组培训情况详情
     * @param id id
     * @return 班组培训情况详情
     */
    TrainRecordResDTO detail(String id);

    /**
     * 新增班组培训情况
     * @param trainRecordReqDTO 班组培训情况参数
     */
    void add(CurrentLoginUser currentLoginUser,TrainRecordReqDTO trainRecordReqDTO);

    /**
     * 编辑班组培训情况
     * @param trainRecordReqDTO 班组培训情况参数
     */
    void modify(TrainRecordReqDTO trainRecordReqDTO);

    /**
     * 删除班组培训情况
     * @param ids ids
     */
    void delete(List<String> ids);
}
