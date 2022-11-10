package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.InboundOrder;


import java.util.List;

public interface IInboundOrder {

    List<BatchStockDTO> save(InboundOrderDTO inboundOrderDTO);

    InboundOrder findById (Long id);

    List<BatchStockDTO> update(Long id, InboundOrderDTO inboundOrderDTO);


}
