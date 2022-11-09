package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
}
