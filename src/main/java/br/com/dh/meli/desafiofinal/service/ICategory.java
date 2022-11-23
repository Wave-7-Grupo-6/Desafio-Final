package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.CategoryDTO;
import br.com.dh.meli.desafiofinal.model.Category;

import java.util.List;

/**
 * The interface Category.
 */
public interface ICategory {
    /**
     * Find by id category.
     *
     * @param id the id
     * @return the category
     */
    Category findById(Long id);

    /**
     * Find by name category.
     *
     * @param name the name
     * @return the category
     */
    Category findByName(String name);

    /**
     * Save.
     *
     * @param categoryDTO the category dto
     */
    void save(CategoryDTO categoryDTO);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Category> findAll();
}
