package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Purchase order.
 */
public interface IPurchaseOrder {
    /**
     * Save big decimal.
     *
     * @param purchaseOrderDTO the purchase order dto
     * @return the big decimal
     */
    BigDecimal save(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<PurchaseOrder> getAll();

    /**
     * Find by id purchase order.
     *
     * @param id the id
     * @return the purchase order
     */
    PurchaseOrder findById(Long id);

    /**
     * Update status to delivered purchase order.
     *
     * @param id the id
     * @return the purchase order
     */
    PurchaseOrder updateStatusToDelivered(Long id);
}
