package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.repository.WarehouseRepo;
import br.com.dh.meli.desafiofinal.service.IWarehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Warehouse service.
 */
@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouse {
    private final WarehouseRepo repo;

    /**
     * If the warehouse is not found, throw an exception.
     *
     * @param id The id of the warehouse you want to find.
     * @return Optional<Warehouse>
     */
    @Override
    public Warehouse findById(Long id) {
        Optional<Warehouse> opWarehouse = repo.findById(id);

        if(opWarehouse.isEmpty()) throw new NoSuchElementException("Warehouse not found!");

        return opWarehouse.get();
    }

    /**
     * Save a new warehouse with the given name.
     *
     * @param warehouse The warehouse object that is being passed in from the front end.
     * @return A new Warehouse object is being returned.
     */
    @Override
    public Warehouse save(WarehouseDTO warehouse) {
        return repo.save(new Warehouse(null, warehouse.getName(), null));
    }

    /**
     * Find all warehouses and return them as a list.
     *
     * @return A list of all warehouses
     */
    @Override
    public List<Warehouse> findAll() {
        return repo.findAll();
    }
}
