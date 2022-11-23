package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.security.JwtTokenFilter;
import br.com.dh.meli.desafiofinal.service.ICategory;
import br.com.dh.meli.desafiofinal.service.IWarehouse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import br.com.dh.meli.desafiofinal.dto.CategoryDTO;
import br.com.dh.meli.desafiofinal.model.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hamcrest.CoreMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@WebMvcTest(WarehouseController.class)
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IWarehouse warehouseService;

    @MockBean
    private JwtTokenFilter filter;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


    @Test
    void getAll_returnsWarehouses_whenSuccess() throws Exception {
        List<Warehouse> warehouses = List.of(getWarehouse());

        when(warehouseService.findAll()).thenReturn(warehouses);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/warehouse")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(getWarehouse().getId().intValue())))
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(getWarehouse().getName())));
    }

    @Test
    void findById_returnsWarehouse_whenSuccess() throws Exception {
        Warehouse warehouse = getWarehouse();

        when(warehouseService.findById(anyLong())).thenReturn(warehouse);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/warehouse/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(warehouse.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(warehouse.getName())));
    }

    @Test
    void save_returnsWarehouse_whenSuccess() throws Exception {
        Warehouse warehouse = getWarehouse();
        WarehouseDTO warehouseDTO = new WarehouseDTO(warehouse);

        when(warehouseService.save(warehouseDTO)).thenReturn(warehouse);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(warehouseDTO)));

        resultActions.andExpect(status().isCreated());
    }
}
