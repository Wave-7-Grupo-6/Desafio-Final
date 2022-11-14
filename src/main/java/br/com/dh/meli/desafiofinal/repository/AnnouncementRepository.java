package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCategory_Id(Long cat_id);
    List<Announcement> findByProductType_Id(Long prod_id);
}
