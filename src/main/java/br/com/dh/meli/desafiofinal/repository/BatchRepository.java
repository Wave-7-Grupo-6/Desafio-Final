package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BatchRepository extends JpaRepository <Batch, Long> {
    List<Batch> findByDueDateIsBefore(LocalDate date);

    @Query("SELECT b FROM Batch b WHERE b.announcement.category.name = :name AND b.dueDate <= :date")
    List<Batch> findBatchByCategoryAndDueDate(LocalDate date, String name);
}
