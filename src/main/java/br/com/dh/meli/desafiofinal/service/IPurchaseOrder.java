package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;

import java.math.BigDecimal;
import java.util.List;

public interface IPurchaseOrder {
    BigDecimal save(PurchaseOrderDTO purchaseOrderDTO);
    List<PurchaseOrder> getAll();
    PurchaseOrder findById(Long id);
}
