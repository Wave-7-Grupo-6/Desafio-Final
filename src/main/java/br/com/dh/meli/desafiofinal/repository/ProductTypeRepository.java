package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
