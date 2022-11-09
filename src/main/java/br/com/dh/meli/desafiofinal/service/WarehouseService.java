package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.repository.WarehouseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouse{
    private final WarehouseRepo repo;

    @Override
    public Optional<Warehouse> findById(Long id) {
        Optional<Warehouse> opWarehouse = repo.findById(id);

        if(opWarehouse.isEmpty()) throw new NoSuchElementException("Warehouse not found!");

        return opWarehouse;
    }

    @Override
    public void save(WarehouseDTO warehouse) {
        repo.save(new Warehouse(null, warehouse.getName(), null));
    }

    @Override
    public List<Warehouse> findAll() {
        return repo.findAll();
    }
}
