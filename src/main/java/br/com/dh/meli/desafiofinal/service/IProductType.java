package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.ProductType;

/**
 * The interface Product type.
 */
public interface IProductType {
    /**
     * Find by id product type.
     *
     * @param id the id
     * @return the product type
     */
    ProductType findById(Long id);
}
