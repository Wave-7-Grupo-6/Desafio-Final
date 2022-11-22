package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
