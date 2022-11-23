package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchDTO;
import br.com.dh.meli.desafiofinal.model.Batch;

import java.util.List;

/**
 * The interface Batch.
 */
public interface IBatch {
    /**
     * Save batch.
     *
     * @param batch the batch
     * @return the batch
     */
    Batch save(Batch batch);

    /**
     * Find by due date is before list.
     *
     * @param days      the days
     * @param sectionId the section id
     * @param sellerId  the seller id
     * @return the list
     */
    List<BatchDTO> findByDueDateIsBefore(int days, Long sectionId, Long sellerId);

    /**
     * Find by days and category and order per due date list.
     *
     * @param days     the days
     * @param category the category
     * @return the list
     */
    List<Batch> findByDaysAndCategoryAndOrderPerDueDate(Integer days, String category);

    /**
     * Update stock batch.
     *
     * @param batchId   the batch id
     * @param productId the product id
     * @param quantity  the quantity
     * @return the batch
     */
    Batch updateStock(Long batchId, Long productId, Integer quantity);

    /**
     * Find by id batch.
     *
     * @param id the id
     * @return the batch
     */
    Batch findById(Long id);
}
