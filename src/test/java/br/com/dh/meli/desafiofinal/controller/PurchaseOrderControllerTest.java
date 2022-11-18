package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import br.com.dh.meli.desafiofinal.service.IPurchaseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getPurchaseOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseOrderController.class)
class PurchaseOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IPurchaseOrder purchaseOrderService;

    @Test
    void save_returnTotal_whenSuccess() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(getPurchaseOrder());
        BigDecimal total = getPurchaseOrder().getTotalPrice();
        when(purchaseOrderService.save(any(PurchaseOrderDTO.class))).thenReturn(total);

        mockMvc.perform(
                post("/api/v1/purchase-order")
                        .content(mapper.writeValueAsString(purchaseOrderDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", CoreMatchers.is(total.intValue())));
    }

    @Test
    void update_returnUpdatedPurchaseOrder_whenSuccess() throws Exception {
        PurchaseOrder purchaseOrder = getPurchaseOrder();
        purchaseOrder.setOrderStatus(OrderStatus.DELIVERED);
        when(purchaseOrderService.updateStatusToDelivered(purchaseOrder.getId())).thenReturn(purchaseOrder);

        mockMvc.perform(
                        put("/api/v1/purchase-order/{id}", purchaseOrder.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderStatus", CoreMatchers.is(purchaseOrder.getOrderStatus().toString())));
    }

    @Test
    void getById_returnPurchaseOrder_whenSuccess() throws Exception {
        PurchaseOrder purchaseOrder = getPurchaseOrder();
        when(purchaseOrderService.findById(purchaseOrder.getId())).thenReturn(purchaseOrder);

        mockMvc.perform(
                        get("/api/v1/purchase-order/{id}", purchaseOrder.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(purchaseOrder.getId().intValue())))
                .andExpect(jsonPath("$.date", CoreMatchers.is(purchaseOrder.getDate().toString())))
                .andExpect(jsonPath("$.orderStatus", CoreMatchers.is(purchaseOrder.getOrderStatus().toString())))
                .andExpect(jsonPath("$.totalPrice", CoreMatchers.is(purchaseOrder.getTotalPrice().intValue())))
                .andExpect(jsonPath("$.buyerId", CoreMatchers.is(purchaseOrder.getClient().getId().intValue())))
                .andExpect(jsonPath("$.products", hasSize(1)));
    }
}