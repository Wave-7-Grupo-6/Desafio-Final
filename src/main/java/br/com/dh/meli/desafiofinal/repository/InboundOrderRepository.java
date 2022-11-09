package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundOrderRepository extends JpaRepository <InboundOrder, Long> {
}
