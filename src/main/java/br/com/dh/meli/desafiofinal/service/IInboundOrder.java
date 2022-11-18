package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.InboundOrder;


import java.util.List;

/**
 * The interface Inbound order.
 */
public interface IInboundOrder {

    /**
     * Save list.
     *
     * @param inboundOrderDTO the inbound order dto
     * @return the list
     */
    List<BatchStockDTO> save(InboundOrderDTO inboundOrderDTO);

    /**
     * Find by id inbound order.
     *
     * @param id the id
     * @return the inbound order
     */
    InboundOrder findById (Long id);

    /**
     * Update list.
     *
     * @param id              the id
     * @param inboundOrderDTO the inbound order dto
     * @return the list
     */
    List<BatchStockDTO> update(Long id, InboundOrderDTO inboundOrderDTO);


}
