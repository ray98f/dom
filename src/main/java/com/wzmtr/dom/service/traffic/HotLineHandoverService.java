package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.hotline.HandoverAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 客运部-需转交其他部门做处理事项
 * @author  Ray
 * @version 1.0
 * @date 2024/05/06
 */
public interface HotLineHandoverService {

    /**
     * 分页查询需转交其他部门做处理事项记录列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 需转交其他部门做处理事项记录列表
     */
    Page<HotLineHandoverListResDTO> pageRecord(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO);

    /**
     * 分页查询需转交其他部门做处理事项详情列表
     * @param id id
     * @param date 日期
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 需转交其他部门做处理事项详情列表
     */
    Page<HotLineHandoverDetailResDTO> pageInfo(String id, String date, String dataType, String startDate,
                                               String endDate, PageReqDTO pageReqDTO);

    /**
     * 新增需转交其他部门做处理事项记录
     * @param handoverAddReqDTO 需转交其他部门做处理事项记录
     */
    void addRecord(HotLineHandoverAddReqDTO handoverAddReqDTO);

    /**
     * 新增需转交其他部门做处理事项详情
     * @param handoverAddDataReqDTO 需转交其他部门做处理事项详情
     */
    void addInfo(HandoverAddDataReqDTO handoverAddDataReqDTO);

    /**
     * 编辑需转交其他部门做处理事项详情
     * @param handoverAddDataReqDTO 需转交其他部门做处理事项详情参数
     */
    void modifyInfo(HandoverAddDataReqDTO handoverAddDataReqDTO);

    /**
     * 删除需转交其他部门做处理事项记录
     * @param ids ids
     */
    void deleteRecord(List<String> ids);

    /**
     * 删除需转交其他部门做处理事项详情
     * @param ids ids
     */
    void deleteOrder(List<String> ids);

    /**
     * 自动更新(周报/月报)报表统计
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     */
    void autoModifyByDaily(String dataType,String startDate,String endDate);
}
