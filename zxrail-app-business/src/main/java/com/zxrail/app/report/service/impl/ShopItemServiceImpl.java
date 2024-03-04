package com.zxrail.app.report.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import com.zxrail.app.report.convert.ShopItemSvcConvert;
import com.zxrail.app.report.model.query.ShopItemQueryDTO;
import com.zxrail.app.report.mapper.ShopItemMapper;
import com.zxrail.app.report.model.cmd.ShopItemCmdDTO;
import com.zxrail.app.report.model.ShopItemDTO;
import com.zxrail.app.report.model.po.ShopItemPo;
import com.zxrail.app.report.service.ShopItemService;

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
public class ShopItemServiceImpl extends ServiceImpl<ShopItemMapper, ShopItemPo> implements ShopItemService {

    @Override
    public Long save(ShopItemCmdDTO cmdDTO) {
        ShopItemPo savePo = ShopItemSvcConvert.INSTANCE.cmdDTO2Po(cmdDTO);
        save(savePo);
        return savePo.getId();
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(ShopItemCmdDTO cmdDTO) {
        return updateById(ShopItemSvcConvert.INSTANCE.cmdDTO2Po(cmdDTO));
    }

    @Override
    public List<ShopItemDTO> listDTO(ShopItemQueryDTO queryDTO) {
        QueryWrapper qw = QueryWrapper.create();
            // .from(SHOP_ITEM_PO)
            // .orderBy(SHOP_ITEM_PO.CREATE_TIME.desc());
        List<ShopItemPo> list = list(qw);
        return ShopItemSvcConvert.INSTANCE.po2DTO(list);
    }

    @Override
    public ShopItemDTO getDTOById(Long id) {
        ShopItemPo one = mapper.selectOneWithRelationsById(id);
        return ShopItemSvcConvert.INSTANCE.po2DTO(one);
    }

    @Override
    public Page<ShopItemDTO> page(ShopItemQueryDTO queryDTO) {
        QueryWrapper qw = QueryWrapper.create();
            // .from(SHOP_ITEM_PO)
            // .orderBy(SHOP_ITEM_PO.CREATE_TIME.desc());
        return ShopItemSvcConvert.INSTANCE.po2DTO(page(queryDTO.toPage(), qw));
    }

}