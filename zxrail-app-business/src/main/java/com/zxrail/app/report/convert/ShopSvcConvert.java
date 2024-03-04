package com.zxrail.app.report.convert;

import com.mybatisflex.core.paginate.Page;
import com.zxrail.app.report.model.ShopDTO;
import com.zxrail.app.report.model.cmd.ShopCmdDTO;
import com.zxrail.app.report.model.po.ShopPo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ShopSvcConvert
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Mapper
public interface ShopSvcConvert {

    ShopSvcConvert INSTANCE = Mappers.getMapper(ShopSvcConvert.class);

    /**
     * cmdDTO -> po
     *
     * @param cmdDTO cmdDTO
     * @return po
     */
    ShopPo cmdDTO2Po(ShopCmdDTO cmdDTO);

    /**
     * po -> dto
     *
     * @param page page
     * @return dto
     */
    Page<ShopDTO> po2DTO(Page<ShopPo> page);

    /**
     * po -> dto
     *
     * @param list list
     * @return dto
     */
    List<ShopDTO> po2DTO(List<ShopPo> list);

    /**
     * po -> dto
     *
     * @param po po
     * @return dto
     */
    ShopDTO po2DTO(ShopPo po);

}
