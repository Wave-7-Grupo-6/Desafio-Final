package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchDTO;
import br.com.dh.meli.desafiofinal.exceptions.NoCompatibleException;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.OutOfStockException;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatch{
    private final BatchRepository repo;
    private final ISeller sellerService;
    private final ISection sectionService;

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

    @Override
    public List<BatchDTO> findByDueDateIsBefore(int days, Long sectionId, Long sellerId) {
        validations(sellerId, sectionId);
        return repo.findByDueDateIsBefore(LocalDate.now().plusDays(days)).stream().map(BatchDTO::new).collect(Collectors.toList());
    }

    public List<Batch> findByDaysAndCategoryAndOrderPerDueDate(Integer days, String category) {
        List<Batch> batches = repo.findBatchByCategoryAndDueDate(LocalDate.now().plusDays(days), category);
        return batches;
    }

    private void validations(Long sellerId, Long sectionId) {
        Seller seller = sellerService.findById(sellerId);
        Section section = sectionService.findById(sectionId);

        if(seller == null || section == null){
            throw new NotFoundException("Seller or Section not found.");
        }
    }
}
