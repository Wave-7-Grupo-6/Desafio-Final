package br.com.dh.meli.desafiofinal.controller;


import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getBatchStockDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InboundOrderController.class)
public class InboundOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IInboundOrder inboundOrderService;

    @Test
    void save_returnBatchStockDTO_whenSuccess() throws Exception {
        List<Batch> batchList;
        InboundOrder inboundOrder = new InboundOrder(2L, LocalDate.now(), 1, batchList);
        List<BatchStockDTO> batchStockDTO =new ArrayList<>();
        batchStockDTO.add(getBatchStockDTO());
        when(inboundOrderService.save(any())).thenReturn(batchStockDTO);

        mockMvc.perform(
                        post("/api/v1/fresh-products/inboundorder/")
                                .content(mapper.writeValueAsString(inboundOrder))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", CoreMatchers.is(batchStockDTO)));

    }
}
