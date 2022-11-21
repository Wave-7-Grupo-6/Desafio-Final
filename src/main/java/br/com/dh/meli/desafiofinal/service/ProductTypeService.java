package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.ProductType;
import br.com.dh.meli.desafiofinal.repository.ProductTypeRepository;
import br.com.dh.meli.desafiofinal.service.IProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * The type Product type service.
 */
@Service
@RequiredArgsConstructor
public class ProductTypeService implements IProductType {
    private final ProductTypeRepository repo;

    /**
     * Find a product type by id, or throw an exception if it doesn't exist.
     *
     * @param id The id of the product type to be found.
     * @return A ProductType object
     */
    @Override
    public ProductType findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Product type not found"));
    }
}
