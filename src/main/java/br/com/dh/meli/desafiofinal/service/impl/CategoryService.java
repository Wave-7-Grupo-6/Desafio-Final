package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.CategoryDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.repository.CategoryRepository;
import br.com.dh.meli.desafiofinal.service.ICategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void save(CategoryDTO categoryDTO) {
        repository.save(new Category(null, categoryDTO.getName(), categoryDTO.getTemperature(), null, null));
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
