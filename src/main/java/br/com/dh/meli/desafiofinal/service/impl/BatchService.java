package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.BatchDTO;
import br.com.dh.meli.desafiofinal.exceptions.NoCompatibleException;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.OutOfStockException;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import br.com.dh.meli.desafiofinal.service.IBatch;
import br.com.dh.meli.desafiofinal.service.ISection;
import br.com.dh.meli.desafiofinal.service.ISeller;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Batch service.
 */
@Service
@RequiredArgsConstructor
public class BatchService implements IBatch {
    private final BatchRepository repo;
    private final ISeller sellerService;
    private final ISection sectionService;

    /**
     * Save the batch to the database.
     *
     * @param batch The batch object that we want to save.
     * @return The batch object that was saved.
     */
    @Override
    public Batch save(Batch batch) {
        return repo.save(batch);
    }

    /**
     * If the batch is not for the product, throw a NotFoundException. If the quantity is greater than the stock, throw an
     * OutOfStockException. Otherwise, update the stock.
     *
     * @param batchId The id of the batch you want to update
     * @param productId The id of the product that is being updated.
     * @param quantity The quantity of the product that is being sold.
     * @return The updated batch.
     */
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

    /**
     * If the batch is found, return it, otherwise throw an exception.
     *
     * @param id The id of the batch to be found.
     * @return A Batch object.
     */
    @Override
    public Batch findById(Long id) {
        return repo.findById(id).orElseThrow(()->new NotFoundException("BatchStock not found."));
    }

    /**
     * > Find all batches that are due in the next `days` days, and return them as a list of `BatchDTO` objects
     *
     * @param days The number of days from now to look for batches.
     * @param sectionId The id of the section you want to search for batches in.
     * @param sellerId The sellerId of the seller who owns the section.
     * @return A list of BatchDTOs
     */
    @Override
    public List<BatchDTO> findByDueDateIsBefore(int days, Long sectionId, Long sellerId) {
        validations(sellerId, sectionId);
        return repo.findByDueDateIsBefore(LocalDate.now().plusDays(days)).stream().map(BatchDTO::new).collect(Collectors.toList());
    }

    /**
     * "Find all batches that are due in the next X days and are of a specific category, and order them by due date."
     *
     * The function is a bit long, but it's not too bad. It's a bit hard to read, but it's not too bad
     *
     * @param days The number of days from today to search for batches.
     * @param category The category of the batch.
     * @return A list of batches that are due in the next x days and are of a certain category.
     */
    @Override
    public List<Batch> findByDaysAndCategoryAndOrderPerDueDate(Integer days, String category) {
        List<Batch> batches = repo.findBatchByCategoryAndDueDate(LocalDate.now().plusDays(days), category);
        return batches;
    }

    /**
     * > This function validates if the seller and section exist
     *
     * @param sellerId The id of the seller.
     * @param sectionId The id of the section that you want to add the seller to.
     */
    private void validations(Long sellerId, Long sectionId) {
        Seller seller = sellerService.findById(sellerId);
        Section section = sectionService.findById(sectionId);

        if(seller == null || section == null){
            throw new NotFoundException("Seller or Section not found.");
        }
    }
}
