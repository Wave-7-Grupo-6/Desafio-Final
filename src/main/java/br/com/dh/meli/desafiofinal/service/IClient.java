package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;

import java.util.List;

/**
 * The interface Client.
 */
public interface IClient {
    /**
     * Save client.
     *
     * @param clientDTO the client dto
     * @return the client
     */
    Client save(ClientDTO clientDTO);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Client> findAll();

    /**
     * Find by id client.
     *
     * @param id the id
     * @return the client
     */
    Client findById(Long id);
}
