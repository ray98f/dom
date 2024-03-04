package com.zxrail.app.report.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import com.zxrail.app.report.model.query.ShopQueryDTO;
import com.zxrail.app.report.convert.ShopSvcConvert;
import com.zxrail.app.report.mapper.ShopMapper;
import com.zxrail.app.report.model.cmd.ShopCmdDTO;
import com.zxrail.app.report.model.ShopDTO;
import com.zxrail.app.report.model.po.ShopPo;
import com.zxrail.app.report.service.ShopService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务层实现。
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, ShopPo> implements ShopService {

    @Override
    public Long save(ShopCmdDTO cmdDTO) {
        ShopPo savePo = ShopSvcConvert.INSTANCE.cmdDTO2Po(cmdDTO);
        save(savePo);
        return savePo.getId();
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(ShopCmdDTO cmdDTO) {
        return updateById(ShopSvcConvert.INSTANCE.cmdDTO2Po(cmdDTO));
    }

    @Override
    public List<ShopDTO> listDTO(ShopQueryDTO queryDTO) {
        QueryWrapper qw = QueryWrapper.create();
            // .from(SHOP_PO)
            // .where(SHOP_PO.STATUS.eq(queryDTO.getStatus()))
            // .orderBy(SHOP_PO.CREATE_TIME.desc());
        List<ShopPo> list = list(qw);
        return ShopSvcConvert.INSTANCE.po2DTO(list);
    }

    @Override
    public ShopDTO getDTOById(Long id) {
        ShopPo one = mapper.selectOneWithRelationsById(id);
        return ShopSvcConvert.INSTANCE.po2DTO(one);
    }

    @Override
    public Page<ShopDTO> page(ShopQueryDTO queryDTO) {
        QueryWrapper qw = QueryWrapper.create();
            // .from(SHOP_PO)
            // .orderBy(SHOP_PO.CREATE_TIME.desc());
        return ShopSvcConvert.INSTANCE.po2DTO(page(queryDTO.toPage(), qw));
    }

}