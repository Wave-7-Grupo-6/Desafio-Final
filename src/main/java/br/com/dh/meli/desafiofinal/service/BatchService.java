package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.OutOfStockException;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatch{

    private final BatchRepository repo;

    @Override
    public Batch save(Batch batch) {
            return repo.save(batch);
        }

    @Override
    public Batch updateStock(Long batchId, Long productId, Integer quantity) {
        Batch batch = findById(batchId);
        if(batch.getAnnouncement().getId() != productId){
            throw new NotFoundException("BatchStock not found.");
        }
        if(quantity > batch.getProductQuantity()){
            throw new OutOfStockException("There is not enough stock in this batch");
        }
        batch.setProductQuantity(batch.getProductQuantity() - quantity);
        return save(batch);
    }

    @Override
    public Batch findById(Long id) {
        return repo.findById(id).orElseThrow(()->new NotFoundException("BatchStock not found."));
    }


}
