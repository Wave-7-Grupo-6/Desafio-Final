package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategory {

    private final CategoryRepository repository;

    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found."));
    }

    @Override
    public Category findByName(String name) {
        Category category = repository.findByName(name);

        if(category == null) throw new NotFoundException("Category not found");

        return category;
    }
}
