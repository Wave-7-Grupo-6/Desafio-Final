package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getClient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository repository;

    private IClient service;

    @BeforeEach
    void setup(){
        service = new ClientService(repository);
    }

    @Test
    void save_returnClient_whenSuccess() {
        Client client = getClient();
        when(repository.save(any())).thenReturn(client);

        Client savedClient = service.save(new ClientDTO(client));

        assertThat(savedClient.getId()).isEqualTo(getClient().getId());
        assertThat(savedClient.getName()).isEqualTo(getClient().getName());
    }

    @Test
    void findAll() {
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