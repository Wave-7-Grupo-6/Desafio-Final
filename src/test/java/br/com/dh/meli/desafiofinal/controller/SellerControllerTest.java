package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.service.ISeller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getSeller;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SellerController.class)
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ISeller sellerService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void save_returnCreatedStatus_whenSellerValid() throws Exception {
        SellerDTO sellerDTO = new SellerDTO(getSeller());
        when(sellerService.save(any(SellerDTO.class))).thenReturn(getSeller());
        mockMvc.perform(
                post("/api/v1/seller")
                        .content(mapper.writeValueAsString(sellerDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(getSeller().getName())));
        verify(this.sellerService, times(1)).save(any(SellerDTO.class));
    }

    @Test
    void findAll_returnSellerList_whenSellerExists() throws Exception {
        when(sellerService.findAll()).thenReturn(List.of(getSeller()));

        mockMvc.perform(
                        get("/api/v1/seller")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(getSeller().getName())));
    }

    @Test
    void findAll_ThrowsNotFoundException_whenSellerDoesntExists() throws Exception {
        when(sellerService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                        get("/api/v1/seller")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findById_returnSeller_whenSellerExists() throws Exception {
        when(sellerService.findById(anyLong())).thenReturn(getSeller());

        mockMvc.perform(
                        get("/api/v1/seller/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(getSeller().getName())));
    }
}