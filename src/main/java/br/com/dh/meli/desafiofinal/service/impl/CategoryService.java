package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.CategoryDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.repository.CategoryRepository;
import br.com.dh.meli.desafiofinal.service.ICategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Category service.
 */
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategory {

    private final CategoryRepository repository;

    /**
     * If the category exists, return it, otherwise throw an exception.
     *
     * @param id The id of the category to be found.
     * @return A category object.
     */
    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found."));
    }

    /**
     * "Find a category by name, if it doesn't exist, throw an exception."
     *
     * The first thing we do is call the repository's findByName function. This function is defined in the
     * CategoryRepository interface
     *
     * @param name The name of the method.
     * @return A Category object
     */
    @Override
    public Category findByName(String name) {
        Category category = repository.findByName(name);

        if(category == null) throw new NotFoundException("Category not found");

        return category;
    }

    /**
     * Save a new category to the database.
     *
     * @param categoryDTO This is the object that we are going to save in the database.
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        repository.save(new Category(null, categoryDTO.getName(), categoryDTO.getTemperature(), null, null));
    }

    /**
     * Find all categories and return them as a list.
     *
     * @return A list of all the categories in the database.
     */
    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
