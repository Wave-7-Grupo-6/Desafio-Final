package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Client repository.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
