package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.SecurityCleaningReqDTO;
import com.wzmtr.dom.dto.res.operate.SecurityCleaningResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运营-安检及保洁情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Mapper
@Repository
public interface SecurityCleaningMapper {

    /**
     * 分页查询安检及保洁情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 安检及保洁情况列表
     */
    Page<SecurityCleaningResDTO> page(Page<SecurityCleaningResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取安检及保洁情况详情
     * @param id id
     * @return 安检及保洁情况详情
     */
    SecurityCleaningResDTO detail(String id);

    /**
     * 查询当天安检及保洁情况是否已存在
     * @param securityCleaningReqDTO 安检及保洁情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(SecurityCleaningReqDTO securityCleaningReqDTO);

    /**
     * 新增安检及保洁情况
     * @param securityCleaningReqDTO 安检及保洁情况参数
     */
    void add(SecurityCleaningReqDTO securityCleaningReqDTO);

    /**
     * 编辑安检及保洁情况
     * @param securityCleaningReqDTO 安检及保洁情况参数
     */
    void modify(SecurityCleaningReqDTO securityCleaningReqDTO);

    /**
     * 删除安检及保洁情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
