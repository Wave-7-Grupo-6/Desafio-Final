package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.IFavoriteList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FavoriteProductController.class)
public class FavoriteProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IFavoriteList service;

    @MockBean
    private IClient clientService;








}
