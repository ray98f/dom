package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.operate.HotLineReqDTO;
import com.wzmtr.dom.dto.res.operate.HotLineResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.HotLineMapper;
import com.wzmtr.dom.service.operate.HotLineService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运营-服务热线情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Service
public class HotLineServiceImpl implements HotLineService {

    @Autowired
    private HotLineMapper hotLineMapper;

    @Override
    public Page<HotLineSummaryDetailResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return hotLineMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public HotLineResDTO detail(String id, String startDate, String endDate) {
        HotLineResDTO res = new HotLineResDTO();
        res.setHotLineSummary(hotLineMapper.detailSummary(id,startDate,endDate));
        res.setHotLineImportant(hotLineMapper.detailImportant(id,startDate,endDate));
        return res;
    }

    @Override
    public void add(HotLineReqDTO securityCleaningReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = hotLineMapper.selectIsExist(securityCleaningReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期服务热线情况数据已存在，无法重复新增");
        }
        securityCleaningReqDTO.setId(TokenUtils.getUuId());
        securityCleaningReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        hotLineMapper.add(securityCleaningReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            hotLineMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
