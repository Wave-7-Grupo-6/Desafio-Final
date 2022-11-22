package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.Charities;
import br.com.dh.meli.desafiofinal.service.ICharities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getCharities;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharitiesController.class)
public class CharitiesControllerTest {

    @MockBean
    private ICharities charitiesService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void save_returnCharitiesAndCreatedStatus_whenCharitiesValid() throws Exception {
        Charities charities = getCharities();
        when(charitiesService.save(any())).thenReturn(charities);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/charities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(charities)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(charities.getName())))
                .andExpect(jsonPath("$.cnpj", CoreMatchers.is(charities.getCnpj())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(charities.getEmail())))
                .andExpect(jsonPath("$.phone", CoreMatchers.is(charities.getPhone())));
    }
}
