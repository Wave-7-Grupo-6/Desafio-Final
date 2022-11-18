package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Purchase order repository.
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
