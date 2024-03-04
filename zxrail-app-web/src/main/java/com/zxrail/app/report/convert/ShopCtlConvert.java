package com.zxrail.app.report.convert;

import com.mybatisflex.core.paginate.Page;

import com.zxrail.app.report.model.query.ShopQueryDTO;
import com.zxrail.app.report.model.ShopDTO;
import com.zxrail.app.report.model.cmd.ShopCmdDTO;
import com.zxrail.app.report.dto.req.ShopQueryReqDTO;
import com.zxrail.app.report.dto.req.ShopSaveReqDTO;
import com.zxrail.app.report.dto.req.ShopUpdateReqDTO;
import com.zxrail.app.report.dto.res.ShopGetRespDTO;
import com.zxrail.app.report.dto.res.ShopListRespDTO;
import com.zxrail.app.report.dto.res.ShopPageRespDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ShopCtlConvert
 *
 * @author xzrail-app
 * @since 1.0.0
 */
@Mapper
public interface ShopCtlConvert {

    ShopCtlConvert INSTANCE = Mappers.getMapper(ShopCtlConvert.class);

    /**
     * saveReqDTO -> cmdDTO
     *
     * @param saveReqDTO saveReqDTO
     * @return cmdDTO
     */
    ShopCmdDTO saveReq2DTO(ShopSaveReqDTO saveReqDTO);

    /**
     * updateReqDTO -> cmdDTO
     *
     * @param updateReqDTO updateReqDTO
     * @return cmdDTO
     */
    ShopCmdDTO updateReq2DTO(ShopUpdateReqDTO updateReqDTO);

    /**
     * queryReqDTO -> queryDTO
     *
     * @param queryReqDTO queryReqDTO
     * @return queryDTO
     */
    ShopQueryDTO queryReq2DTO(ShopQueryReqDTO queryReqDTO);

    /**
     * dto -> getRespDTO
     *
     * @param dto dto
     * @return getRespDTO
     */
    ShopGetRespDTO getDTO2Resp(ShopDTO dto);

    /**
     * pageDTO -> pageRespDTO
     *
     * @param pageDTO pageDTO
     * @return pageRespDTO
     */
    Page<ShopPageRespDTO> pageDTO2Resp(Page<ShopDTO> pageDTO);

    /**
     * pageDTO -> pageRespDTO
     *
     * @param pageDTO pageDTO
     * @return pageRespDTO
     */
    ShopPageRespDTO pageDTO2Resp(ShopDTO pageDTO);

    /**
     * listDTO -> listRespDTO
     *
     * @param listDTO listDTO
     * @return listRespDTO
     */
    List<ShopListRespDTO> listDTO2Resp(List<ShopDTO> listDTO);

    /**
     * listDTO -> listRespDTO
     *
     * @param listDTO listDTO
     * @return listRespDTO
     */
    ShopListRespDTO listDTO2Resp(ShopDTO listDTO);

}
