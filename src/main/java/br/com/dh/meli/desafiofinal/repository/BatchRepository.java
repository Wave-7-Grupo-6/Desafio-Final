package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BatchRepository extends JpaRepository <Batch, Long> {
    List<Batch> findByDueDateIsBefore(LocalDate date);
}
