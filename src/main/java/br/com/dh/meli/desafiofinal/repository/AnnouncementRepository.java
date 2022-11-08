package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Annoucement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Annoucement, Long> {
}
