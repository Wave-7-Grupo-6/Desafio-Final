package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Annoucement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Annoucement, Long> {
    List<Annoucement> findByCategory_Id(Long cat_id);
}
