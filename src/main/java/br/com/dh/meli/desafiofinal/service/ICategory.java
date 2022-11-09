package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.CategoryDTO;
import br.com.dh.meli.desafiofinal.model.Category;

import java.util.List;

public interface ICategory {
    Category findById(Long id);
    Category findByName(String name);
    void save(CategoryDTO categoryDTO);
    List<Category> findAll();
}
