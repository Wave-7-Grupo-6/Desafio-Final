package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
