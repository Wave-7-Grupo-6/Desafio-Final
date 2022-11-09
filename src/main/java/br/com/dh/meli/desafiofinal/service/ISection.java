package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Section;

import java.util.Optional;

public interface ISection {
    Optional<Section> findById(Long id);
}
