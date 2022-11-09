package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Announcement;

import java.util.List;

public interface IAnnouncement {
    Announcement findById(Long id);
    Announcement save(Announcement announcement);
    List<Announcement> findAll();
}
