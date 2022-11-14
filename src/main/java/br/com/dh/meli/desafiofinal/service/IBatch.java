package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Batch;

public interface IBatch {
    Batch save(Batch batch);
    Batch updateStock(Long batchId, Long productId, Integer quantity);
    Batch findById(Long id);

}
