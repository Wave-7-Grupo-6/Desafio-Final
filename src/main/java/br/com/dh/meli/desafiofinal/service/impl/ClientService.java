package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import br.com.dh.meli.desafiofinal.service.IClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService implements IClient {
    private final ClientRepository repo;
    private final RoleService roleService;

    @Override
    public Client save(ClientDTO clientDTO) {
        Role role = roleService.findByName("ROLE_CLIENT");
        return repo.save(new Client(clientDTO, Set.of(role)));
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
