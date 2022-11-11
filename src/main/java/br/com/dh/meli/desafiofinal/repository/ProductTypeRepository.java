package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
