package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.ProductType;
import br.com.dh.meli.desafiofinal.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductTypeService implements IProductType{
    private final ProductTypeRepository repo;

    @Override
    public ProductType findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Product type not found"));
    }
}
