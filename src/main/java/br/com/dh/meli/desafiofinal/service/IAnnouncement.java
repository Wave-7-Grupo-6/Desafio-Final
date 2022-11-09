package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Annoucement;

import java.util.List;

public interface IAnnouncement {
    Annoucement findById(Long id);
    Annoucement save(Annoucement annoucement);
    List<Annoucement> findByCategory(String category);
}
