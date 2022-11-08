package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exception.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository repository;

    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found."));
    }
}
