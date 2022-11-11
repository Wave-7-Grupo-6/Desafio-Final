package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.service.IPurchaseOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getPurchaseOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PurchaseOrderController.class)
class PurchaseOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IPurchaseOrder purchaseOrderService;

    @Test
    void save_returnPurchaseOrder_whenSuccess() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(getPurchaseOrder());
        when(purchaseOrderService.save(any(PurchaseOrderDTO.class))).thenReturn()
    }

    @Test
    void update() {
    }

    @Test
    void getById() {
    }
}