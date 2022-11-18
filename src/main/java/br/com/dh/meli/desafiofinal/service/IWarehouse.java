package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.controller.WarehouseController;
import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;

import java.util.List;
import java.util.Optional;

/**
 * The interface Warehouse.
 */
public interface IWarehouse {
    /**
     * Find by id warehouse.
     *
     * @param id the id
     * @return the warehouse
     */
    Warehouse findById(Long id);

    /**
     * Save warehouse.
     *
     * @param warehouse the warehouse
     * @return the warehouse
     */
    Warehouse save(WarehouseDTO warehouse);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Warehouse> findAll();
}
