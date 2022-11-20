package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ProductTypeDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;

import java.util.List;

import java.util.List;

/**
 * The interface Announcement.
 */
public interface IAnnouncement {
    /**
     * Find by id announcement.
     *
     * @param id the id
     * @return the announcement
     */

    Announcement findById(Long id);

    /**
     * Find by id announcement.
     *
     * @param id the id
     * @param currency the currency
     * @return the announcement
     */

    Announcement findByIdAndCurrency(Long id, String currency);

    /**
     * Save announcement.
     *
     * @param announcement the announcement
     * @return the announcement
     */
    Announcement save(Announcement announcement);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Announcement> findAll();

    /**
     * Find by category list.
     *
     * @param category the category
     * @return the list
     */
    List<Announcement> findByCategory(String category);

    /**
     * Find by product type list.
     *
     * @param prod_id the prod id
     * @return the list
     */
    List<Announcement> findByProductType(Long prod_id);

    /**
     * Find by product type group by warehouse product type dto.
     *
     * @param prod_id the prod id
     * @return the product type dto
     */
    ProductTypeDTO findByProductTypeGroupByWarehouse(Long prod_id);
}
