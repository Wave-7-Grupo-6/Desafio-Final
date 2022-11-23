package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Announcement repository.
 */
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    /**
     * Find by category id list.
     *
     * @param cat_id the cat id
     * @return the list
     */
    List<Announcement> findByCategory_Id(Long cat_id);

    /**
     * Find by product type id list.
     *
     * @param prod_id the prod id
     * @return the list
     */
    List<Announcement> findByProductType_Id(Long prod_id);
}
