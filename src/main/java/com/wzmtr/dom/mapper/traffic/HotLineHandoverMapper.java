package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineHandoverDO;
import com.wzmtr.dom.dto.req.traffic.hotline.HandoverAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客运部-需转交其他部门做处理事项
 * @author  Ray
 * @version 1.0
 * @date 2024/05/06
 */
@Mapper
@Repository
public interface HotLineHandoverMapper extends BaseMapper<TrafficHotlineHandoverDO> {

    /**
     * 分页查询需转交其他部门做处理事项记录列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 需转交其他部门做处理事项记录列表
     */
    Page<HotLineHandoverListResDTO> pageRecord(Page<HotLineHandoverListResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 分页查询需转交其他部门做处理事项详情列表
     * @param page 分页参数
     * @param id id
     * @param date 日期
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 需转交其他部门做处理事项详情列表
     */
    Page<HotLineHandoverDetailResDTO> pageInfo(Page<HotLineHandoverDetailResDTO> page, String id,
                                               String date, String dataType, String startDate, String endDate);

    /**
     * 根据id获取需转交其他部门做处理事项记录
     * @param id id
     * @return 需转交其他部门做处理事项记录
     */
    HotLineHandoverListResDTO getRecordDetail(String id);

    /**
     * 根据id获取需转交其他部门做处理事项详情
     * @param id id
     * @return 需转交其他部门做处理事项详情
     */
    HotLineHandoverDetailResDTO getInfoDetail(String id);

    /**
     * 查询需转交其他部门做处理事项记录是否已存在
     * @param req 需转交其他部门做处理事项记录参数
     * @return 是否已存在
     */
    Integer selectRecordIsExist(HotLineHandoverAddReqDTO req);

    /**
     * 查询需转交其他部门做处理事项详情是否已修改
     * @param req 需转交其他部门做处理事项详情参数
     * @return 是否已存在
     */
    Integer selectInfoIsExist(HandoverAddDataReqDTO req);

    /**
     * 新增需转交其他部门做处理事项记录
     * @param req 需转交其他部门做处理事项记录参数
     */
    void addRecord(HotLineHandoverAddReqDTO req);

    /**
     * 新增需转交其他部门做处理事项详情
     * @param req 需转交其他部门做处理事项详情
     */
    void addInfo(HandoverAddDataReqDTO req);

    /**
     * 编辑需转交其他部门做处理事项记录
     * @param req 需转交其他部门做处理事项记录参数
     */
    void modifyRecord(HotLineHandoverAddReqDTO req);

    /**
     * 编辑需转交其他部门做处理事项详情
     * @param req 需转交其他部门做处理事项详情
     */
    void modifyInfo(HandoverAddDataReqDTO req);

    /**
     * 删除需转交其他部门做处理事项记录
     * @param ids ids
     * @param userId 用户id
     */
    void deleteRecord(List<String> ids, String userId);

    /**
     * 删除需转交其他部门做处理事项详情
     * @param recordIds 记录ids
     * @param ids ids
     * @param userId 用户id
     */
    void deleteInfo(List<String> recordIds, List<String> ids, String userId);

}
