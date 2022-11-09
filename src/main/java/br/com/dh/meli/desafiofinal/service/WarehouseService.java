package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.repository.WarehouseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouse{
    private WarehouseRepo repo;
    @Override
    public Optional<Warehouse> findById(Long id) {
        return repo.findById(id);
    }
}
