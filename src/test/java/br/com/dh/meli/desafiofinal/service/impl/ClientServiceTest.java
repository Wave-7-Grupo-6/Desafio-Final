package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.IRole;
import br.com.dh.meli.desafiofinal.service.IUser;
import br.com.dh.meli.desafiofinal.service.impl.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getClientRole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getClient;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository repository;

    @Mock
    private IRole roleService;

    @Mock
    private IUser userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private IClient service;

    @BeforeEach
    void setup(){
        service = new ClientService(repository, userService, roleService, passwordEncoder);
    }

    @Test
    void save_returnClient_whenSuccess() {
        Client client = getClient();
        when(repository.save(any())).thenReturn(client);
        when(roleService.findByName("ROLE_CLIENT")).thenReturn(getClientRole());
        when(userService.existsByUsername(anyString())).thenReturn(false);

        Client savedClient = service.save(new ClientDTO(client));

        assertThat(savedClient.getId()).isEqualTo(getClient().getId());
        assertThat(savedClient.getName()).isEqualTo(getClient().getName());
    }

    @Test
    void findAll_returnClient_whenSuccess() {
        when(repository.findAll()).thenReturn(List.of(getClient()));

        List<Client> clientList = service.findAll();
        assertThat(clientList).isNotEmpty();
        assertThat(clientList).hasSize(1);
    }

    @Test
    void findById_returnClient_whenClientExists() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(getClient()));

        Client client = service.findById(1L);

        assertThat(client).isNotNull();
        assertThat(client.getId()).isEqualTo(getClient().getId());
        assertThat(client.getName()).isEqualTo(getClient().getName());
        assertThat(client.getCarts()).isNull();
    }

    @Test
    void findById_throwsNotFoundException_whenClientDoesntExists(){
        when(repository.findById(anyLong())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.findById(1L));
    }
}