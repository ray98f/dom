package com.zxrail.app.report.convert;

import com.mybatisflex.core.paginate.Page;
import com.zxrail.app.report.model.cmd.ShopItemCmdDTO;
import com.zxrail.app.report.model.ShopItemDTO;
import com.zxrail.app.report.model.po.ShopItemPo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ShopItemSvcConvert
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Mapper
public interface ShopItemSvcConvert {

    ShopItemSvcConvert INSTANCE = Mappers.getMapper(ShopItemSvcConvert.class);

    /**
     * cmdDTO -> po
     *
     * @param cmdDTO cmdDTO
     * @return po
     */
    ShopItemPo cmdDTO2Po(ShopItemCmdDTO cmdDTO);

    /**
     * po -> dto
     *
     * @param page page
     * @return dto
     */
    Page<ShopItemDTO> po2DTO(Page<ShopItemPo> page);

    /**
     * po -> dto
     *
     * @param list list
     * @return dto
     */
    List<ShopItemDTO> po2DTO(List<ShopItemPo> list);

    /**
     * po -> dto
     *
     * @param po po
     * @return dto
     */
    ShopItemDTO po2DTO(ShopItemPo po);

}
