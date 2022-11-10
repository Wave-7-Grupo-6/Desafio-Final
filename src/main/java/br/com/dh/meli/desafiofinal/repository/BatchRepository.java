package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository <Batch, Long> {
}
