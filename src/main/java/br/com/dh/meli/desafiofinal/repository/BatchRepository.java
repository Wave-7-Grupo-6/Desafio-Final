package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Batch repository.
 */
public interface BatchRepository extends JpaRepository <Batch, Long> {
    /**
     * Find by due date is before list.
     *
     * @param date the date
     * @return the list
     */
    List<Batch> findByDueDateIsBefore(LocalDate date);

    /**
     * Find batch by category and due date list.
     *
     * @param date the date
     * @param name the name
     * @return the list
     */
    @Query("SELECT b FROM Batch b WHERE b.announcement.category.name = :name AND b.dueDate <= :date")
    List<Batch> findBatchByCategoryAndDueDate(LocalDate date, String name);
}
