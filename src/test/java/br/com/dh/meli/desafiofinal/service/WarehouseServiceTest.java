package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import br.com.dh.meli.desafiofinal.repository.WarehouseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {
    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private WarehouseRepo warehouseRepo;

    private IWarehouse warehouseService;

    @BeforeEach
    void setUp() {
        warehouseService = new WarehouseService(warehouseRepo);
    }

    @Test
    void findById_returnWarehouse_whenWarehouseExists() {
        Warehouse warehouse = getWarehouse();

        when(warehouseRepo.findById(anyLong())).thenReturn(Optional.of(warehouse));

        Warehouse warehouseFound = warehouseService.findById(1L);

        assertThat(warehouseFound).isNotNull();
        assertThat(warehouseFound.getId()).isEqualTo(warehouse.getId());
        assertThat(warehouseFound.getName()).isEqualTo(warehouse.getName());
    }

    @Test
    void save_returnWarehouse_whenWarehouseIsSaved() {
        Warehouse warehouse = getWarehouse();
        WarehouseDTO warehouseDTO = new WarehouseDTO(warehouse);

        when(warehouseRepo.save(any(Warehouse.class))).thenReturn(warehouse);

        Warehouse warehouseSaved = warehouseService.save(warehouseDTO);

        assertThat(warehouseSaved).isNotNull();
        assertThat(warehouseSaved.getId()).isEqualTo(warehouse.getId());
        assertThat(warehouseSaved.getName()).isEqualTo(warehouse.getName());
    }

    @Test
    void findAll_returnWarehouseList_whenWarehouseExists() {
        Warehouse warehouse = getWarehouse();
        List<Warehouse> warehouseList = List.of(warehouse);

        when(warehouseRepo.findAll()).thenReturn(warehouseList);

        List<Warehouse> warehouseListFound = warehouseService.findAll();

        assertThat(warehouseListFound).isNotNull();
        assertThat(warehouseListFound.size()).isEqualTo(warehouseList.size());
        assertThat(warehouseListFound.get(0).getId()).isEqualTo(warehouse.getId());
        assertThat(warehouseListFound.get(0).getName()).isEqualTo(warehouse.getName());
    }

}
