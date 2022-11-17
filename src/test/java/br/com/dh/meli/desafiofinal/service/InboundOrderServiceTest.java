package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InboundOrderServiceTest {

    @Mock
    private InboundOrderRepository repository;
    @Mock
    private ISection section;
    @Mock
    private IAnnouncement announcement;
    @Mock
    private IWarehouse warehouse;

    private IInboundOrder inboundOrderService;

    @BeforeEach
    void setUp() {
        inboundOrderService = new InboundOrderService(repository, section, announcement, warehouse);
    }

    @Test
    void save_returnInboundOrder_whenSuccess() {
        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        when(repository.save(any(InboundOrder.class))).thenReturn(inboundOrder);

        List<BatchStockDTO> inboundOrderSaved = inboundOrderService.save(inboundOrderDTO);

        assertThat(inboundOrderSaved.getId()).isEqualTo(getClient().getId());
        assertThat(inboundOrderSaved.getName()).isEqualTo(getClient().getName());
    }
}
