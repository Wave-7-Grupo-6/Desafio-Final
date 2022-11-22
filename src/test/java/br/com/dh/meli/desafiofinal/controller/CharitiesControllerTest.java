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

import java.util.Collections;
import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getCharities;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void save_returnBadRequestStatus_whenCharitiesInvalid() throws Exception {
        when(charitiesService.save(any())).thenReturn(null);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/charities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getCharities())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAll_returnListCharitiesAndStatusOk_whenSuccess() throws Exception {
        List<Charities> charities = List.of(getCharities());
        when(charitiesService.findAll()).thenReturn(charities);

        mockMvc.perform(
                get("/api/v1/charities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void findAll_ThrowsNotFoundException_whenCharitiesNotFound() throws Exception {
        when(charitiesService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                get("/api/v1/charities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findById_returnCharityAndSucess_whenCharityExist() throws Exception {
        Charities charities = getCharities();
        when(charitiesService.findById(anyLong())).thenReturn(charities);

        mockMvc.perform(
                get("/api/v1/charities/{id}", charities.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(charities.getName())))
                .andExpect(jsonPath("$.cnpj", CoreMatchers.is(charities.getCnpj())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(charities.getEmail())))
                .andExpect(jsonPath("$.phone", CoreMatchers.is(charities.getPhone())));
    }

    @Test
    void findById_ThrowsNotFoundException_whenCharityNotFound() throws Exception {
        when(charitiesService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/api/v1/charities/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_returnCharityAndStatusCreated_whenCharityExist() throws Exception {
        Charities charities = getCharities();
        when(charitiesService.update(anyLong(), any())).thenReturn(charities);

        mockMvc.perform(
                put("/api/v1/charities/{id}", charities.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(charities)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(charities.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(charities.getName())))
                .andExpect(jsonPath("$.cnpj", CoreMatchers.is(charities.getCnpj())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(charities.getEmail())))
                .andExpect(jsonPath("$.phone", CoreMatchers.is(charities.getPhone())));
    }

    @Test
    void update_ThrowsNotFoundException_whenCharityNotFound() throws Exception {
        when(charitiesService.update(anyLong(), any())).thenReturn(null);

        mockMvc.perform(
                put("/api/v1/charities/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(getCharities())))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteById_returnNoContentAndStatusOk_whenSuccess() throws Exception {
        when(charitiesService.deleteById(anyLong())).thenReturn(true);

        mockMvc.perform(
                delete("/api/v1/charities/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteById_ThrowsNotFoundException_whenCharityNotFound() throws Exception {
        when(charitiesService.deleteById(anyLong())).thenReturn(false);

        mockMvc.perform(
                delete("/api/v1/charities/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
