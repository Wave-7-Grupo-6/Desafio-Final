package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Seller repository.
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
