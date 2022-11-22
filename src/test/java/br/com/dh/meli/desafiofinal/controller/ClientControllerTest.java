package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.IUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hamcrest.CoreMatchers;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IClient service;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void save_returnStatusCreated_whenValid() throws Exception {
        Client client = getClient();
        ClientDTO clientDTO = new ClientDTO(client);
        when(service.save(clientDTO)).thenReturn(client);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/client")
                        .content(mapper.writeValueAsString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isCreated());
    }

    @Test
    void findAll_returnsClients_whenValid() throws Exception {
        List<Client> clients = List.of(getClient());
        when(service.findAll()).thenReturn(clients);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", CoreMatchers.is("Client 1")));
    }

    @Test
    void findById_returnsClient_whenValid() throws Exception {
        Client client = getClient();
        when(service.findById(1L)).thenReturn(client);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/client/1")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is("Client 1")));
    }

}
