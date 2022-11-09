package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Annoucement;

public interface IAnnouncement {
    Annoucement findById(Long id);
    Annoucement save(Annoucement annoucement);
}
