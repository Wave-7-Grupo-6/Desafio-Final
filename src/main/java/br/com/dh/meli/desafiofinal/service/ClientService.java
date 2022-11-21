package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientService implements IClient{
    private final ClientRepository repo;

    @Override
    public Client save(ClientDTO clientDTO) {
        return repo.save(new Client(null, clientDTO.getName(), null));
    }

    @Override
    public List<Client> findAll() {
        return repo.findAll();
    }

    @Override
    public Client findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));
    }
}
