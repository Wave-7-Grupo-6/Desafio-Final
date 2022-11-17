package br.com.dh.meli.desafiofinal.controller;


import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import br.com.dh.meli.desafiofinal.service.IInboundOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getInboundOrder;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getSeller;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(InboundOrderController.class)
public class InboundOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IInboundOrder service;

    @Test
    void save_returnStatusCreated_whenValid() throws Exception {

        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        List<BatchStockDTO> expected = new ArrayList<>();
        expected.add(new BatchStockDTO());
        when(service.save(any(InboundOrderDTO.class))).thenReturn(expected);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/fresh-products/inboundorder/")
                        .content(mapper.writeValueAsString(inboundOrderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void save_returnStatusError_whenWarehouseIdIsNotFilled() throws Exception {

        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/fresh-products/inboundorder/")
                        .content(mapper.writeValueAsString(inboundOrderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title",CoreMatchers.is("Parâmetros inválidos")));
    }

    @Test
    void update_returnStatusCreated_whenValid() throws Exception {

        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        List<BatchStockDTO> expected = new ArrayList<>();
        expected.add(new BatchStockDTO());
        when(service.update(anyLong(),any(InboundOrderDTO.class))).thenReturn(expected);

        ResultActions resultActions = mockMvc.perform(
                put("/api/v1/fresh-products/inboundorder/2")
                        .content(mapper.writeValueAsString(inboundOrderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isCreated());
    }

    @Test
    void update_returnStatusCreated_whenTheInboundIsNotFound() throws Exception {

        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        List<BatchStockDTO> expected = null;
        when(service.update(eq(2L),any(InboundOrderDTO.class))).thenReturn(expected);

        ResultActions resultActions = mockMvc.perform(
                put("/api/v1/fresh-products/inboundorder/2")
                        .content(mapper.writeValueAsString(inboundOrderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isNotFound());
    }
}
