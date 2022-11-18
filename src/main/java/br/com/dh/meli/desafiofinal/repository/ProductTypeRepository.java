package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Product type repository.
 */
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
