package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Warehouse;

import java.util.Optional;

public interface IWarehouse {
    Optional<Warehouse> findById(Long id);
}
