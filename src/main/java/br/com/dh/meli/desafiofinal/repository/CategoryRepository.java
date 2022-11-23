package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Category repository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Find by name category.
     *
     * @param name the name
     * @return the category
     */
    Category findByName(String name);
}
