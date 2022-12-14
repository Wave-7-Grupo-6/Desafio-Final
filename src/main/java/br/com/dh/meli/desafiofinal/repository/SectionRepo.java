package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Section repo.
 */
public interface SectionRepo extends JpaRepository<Section, Long> {
}
