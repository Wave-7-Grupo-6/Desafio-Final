package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Charities;

import java.util.List;

public interface ICharities {
    Charities save(Charities charities);

    List<Charities> findAll();

    Charities findById(Long id);

    Charities update(Long id, Charities charity);
}
