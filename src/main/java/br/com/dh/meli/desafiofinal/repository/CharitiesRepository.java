package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Charities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharitiesRepository extends JpaRepository<Charities, Long> {

    Charities save(Charities charities);

    List<Charities> findAll();

    Optional<Charities> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
