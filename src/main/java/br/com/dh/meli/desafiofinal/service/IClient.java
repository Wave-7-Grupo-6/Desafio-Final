package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClient {
    void save(ClientDTO clientDTO);
    List<Client> findAll();
    Optional<Client> findById(Long id);
}
