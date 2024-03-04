package com.zxrail.app.report.convert;

import com.mybatisflex.core.paginate.Page;

import com.zxrail.app.report.model.query.ShopItemQueryDTO;
import com.zxrail.app.report.dto.req.ShopItemQueryReqDTO;
import com.zxrail.app.report.dto.req.ShopItemSaveReqDTO;
import com.zxrail.app.report.dto.req.ShopItemUpdateReqDTO;
import com.zxrail.app.report.model.ShopItemDTO;
import com.zxrail.app.report.model.cmd.ShopItemCmdDTO;
import com.zxrail.app.report.dto.res.ShopItemGetRespDTO;
import com.zxrail.app.report.dto.res.ShopItemListRespDTO;
import com.zxrail.app.report.dto.res.ShopItemPageRespDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ShopItemCtlConvert
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Mapper
public interface ShopItemCtlConvert {

    ShopItemCtlConvert INSTANCE = Mappers.getMapper(ShopItemCtlConvert.class);

    /**
     * saveReqDTO -> cmdDTO
     *
     * @param saveReqDTO saveReqDTO
     * @return cmdDTO
     */
    ShopItemCmdDTO saveReq2DTO(ShopItemSaveReqDTO saveReqDTO);

    /**
     * updateReqDTO -> cmdDTO
     *
     * @param updateReqDTO updateReqDTO
     * @return cmdDTO
     */
    ShopItemCmdDTO updateReq2DTO(ShopItemUpdateReqDTO updateReqDTO);

    /**
     * queryReqDTO -> queryDTO
     *
     * @param queryReqDTO queryReqDTO
     * @return queryDTO
     */
    ShopItemQueryDTO queryReq2DTO(ShopItemQueryReqDTO queryReqDTO);

    /**
     * dto -> getRespDTO
     *
     * @param dto dto
     * @return getRespDTO
     */
    ShopItemGetRespDTO getDTO2Resp(ShopItemDTO dto);

    /**
     * pageDTO -> pageRespDTO
     *
     * @param pageDTO pageDTO
     * @return pageRespDTO
     */
    Page<ShopItemPageRespDTO> pageDTO2Resp(Page<ShopItemDTO> pageDTO);

    /**
     * pageDTO -> pageRespDTO
     *
     * @param pageDTO pageDTO
     * @return pageRespDTO
     */
    ShopItemPageRespDTO pageDTO2Resp(ShopItemDTO pageDTO);

    /**
     * listDTO -> listRespDTO
     *
     * @param listDTO listDTO
     * @return listRespDTO
     */
    List<ShopItemListRespDTO> listDTO2Resp(List<ShopItemDTO> listDTO);

    /**
     * listDTO -> listRespDTO
     *
     * @param listDTO listDTO
     * @return listRespDTO
     */
    ShopItemListRespDTO listDTO2Resp(ShopItemDTO listDTO);

}
