package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import br.com.dh.meli.desafiofinal.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderServiceTest {

    @Mock
    private PurchaseOrderRepository repository;
    @Mock
    private IAnnouncement announcementService;
    @Mock
    private IClient clientService;
    @Mock
    private IBatch batchService;
    private IPurchaseOrder purchaseOrderService;

    @BeforeEach
    void setUp() {
        purchaseOrderService = new PurchaseOrderService(repository, announcementService, clientService, batchService);
    }

    @Test
    void save_returnTotal_whenSuccess() {
        PurchaseOrder purchaseOrder = getPurchaseOrder();
        PurchaseOrderDTO purchaseOrderDTO = getPurchaseOrderDTO();
        when(repository.save(any(PurchaseOrder.class))).thenReturn(purchaseOrder);
        when(announcementService.findById(anyLong())).thenReturn(getAnnouncement());
        when(clientService.findById(anyLong())).thenReturn(getClient());
        when(batchService.findById(anyLong())).thenReturn(getLowIdBatch());

        BigDecimal total = purchaseOrderService.save(purchaseOrderDTO);

        assertThat(total).isEqualTo(purchaseOrder.getTotalPrice());
    }

    @Test
    void update_returnPurchaseOrder_whenPurchaseOrderExists() {
        PurchaseOrder purchaseOrder = getPurchaseOrder();
        when(repository.findById(anyLong())).thenReturn(Optional.of(purchaseOrder));

        assertThat(purchaseOrderService.update(1L).getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    @Test
    void getAll_returnPurchaseOrderList_whenPurchaseOrderExists() {
        PurchaseOrder purchaseOrder = getPurchaseOrder();
        when(repository.findAll()).thenReturn(List.of(purchaseOrder));

        assertThat(purchaseOrderService.getAll()).isNotEmpty();
    }

    @Test
    void findById_returnPurchaseOrder_whenPurchaseOrderExists() {
        PurchaseOrder purchaseOrder = getPurchaseOrder();
        when(repository.findById(anyLong())).thenReturn(Optional.of(purchaseOrder));

        PurchaseOrder foundPurchaseOrder = purchaseOrderService.findById(1L);

        assertThat(foundPurchaseOrder.getId()).isEqualTo(purchaseOrder.getId());
        assertThat(foundPurchaseOrder.getDate()).isEqualTo(purchaseOrder.getDate());
        assertThat(foundPurchaseOrder.getOrderStatus()).isEqualTo(purchaseOrder.getOrderStatus());
    }

    @Test
    void findById_throwsNoSuchElementException_whenPurchaseOrderDoesntExist() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,  ()-> purchaseOrderService.findById(1L));
    }
}