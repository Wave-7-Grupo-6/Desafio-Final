package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import br.com.dh.meli.desafiofinal.repository.WarehouseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        //TODO
    }

    @Test
    void save_returnWarehouse_whenWarehouseIsSaved() {
        //TODO
    }

    @Test
    void findAll_returnWarehouseList_whenWarehouseExists() {
        //TODO
    }

}
