package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface IWarehouse {
    Optional<Warehouse> findById(Long id);
    void save(WarehouseDTO warehouse);
    List<Warehouse> findAll();
}
