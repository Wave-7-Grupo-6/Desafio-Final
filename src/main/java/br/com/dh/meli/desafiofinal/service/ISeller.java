package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.model.Seller;

import java.util.List;

/**
 * The interface Seller.
 */
public interface ISeller {
    /**
     * Find by id seller.
     *
     * @param id the id
     * @return the seller
     */
    Seller findById(Long id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Seller> findAll();

    /**
     * Save seller.
     *
     * @param sellerDTO the seller dto
     * @return the seller
     */
    Seller save(SellerDTO sellerDTO);
}
