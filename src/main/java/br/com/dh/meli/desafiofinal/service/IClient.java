package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;

import java.util.List;

public interface IClient {
    void save(ClientDTO clientDTO);
    List<Client> findAll();
    Client findById(Long id);
}
