package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.Batch;

import java.util.List;

public interface IInboundOrder {

    List<Batch> save(InboundOrderDTO inboundOrderDTO);

}
