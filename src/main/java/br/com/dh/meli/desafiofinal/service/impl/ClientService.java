package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import br.com.dh.meli.desafiofinal.service.IClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Client service.
 */
@Service
@RequiredArgsConstructor
public class ClientService implements IClient {
    private final ClientRepository repo;

    /**
     * Save a new client to the database.
     *
     * @param clientDTO This is the object that will be passed to the method.
     * @return A Client object
     */
    @Override
    public Client save(ClientDTO clientDTO) {
        return repo.save(new Client(null, clientDTO.getName(), null));
    }

    /**
     * Find all clients and return them as a list.
     *
     * @return A list of all the clients in the database.
     */
    @Override
    public List<Client> findAll() {
        return repo.findAll();
    }

    /**
     * Find a client by id, or throw an exception if it doesn't exist.
     *
     * @param id The id of the client to be found.
     * @return A client object
     */
    @Override
    public Client findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));
    }
}
