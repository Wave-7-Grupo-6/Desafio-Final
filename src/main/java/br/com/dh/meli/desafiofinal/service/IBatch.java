package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchDTO;
import br.com.dh.meli.desafiofinal.model.Batch;

import java.util.List;

public interface IBatch {
    Batch save(Batch batch);
    List<BatchDTO> findByDueDateIsBefore(int days, Long sectionId, Long sellerId);
    Batch updateStock(Long batchId, Long productId, Integer quantity);
    Batch findById(Long id);
}
