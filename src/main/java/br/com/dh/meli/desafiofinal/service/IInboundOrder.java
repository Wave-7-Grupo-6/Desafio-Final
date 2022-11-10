package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.InboundOrder;


import java.util.List;

public interface IInboundOrder {

    List<Batch> save(InboundOrderDTO inboundOrderDTO);

    InboundOrder findById (Long id);

    List<Batch> update(Long id, InboundOrderDTO inboundOrderDTO);
}
