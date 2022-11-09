package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements IClient{
    private final ClientRepository repo;

    @Override
    public void save(ClientDTO clientDTO) {
        repo.save(new Client(null, clientDTO.getName(), null));
    }

    @Override
    public List<Client> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<Client> opClient = repo.findById(id);

        if(opClient.isEmpty()) throw new NoSuchElementException("Client not found!");

        return opClient;
    }
}
