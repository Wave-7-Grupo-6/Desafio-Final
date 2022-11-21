package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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
        when(section.findById(anyLong())).thenReturn(getSection());
        when(announcement.findById(anyLong())).thenReturn(getAnnouncement());
        when(warehouse.findById(anyLong())).thenReturn(getWarehouse());

        List<BatchStockDTO> inboundOrderSaved = inboundOrderService.save(inboundOrderDTO);

        assertThat(inboundOrderSaved.get(0).getProductId()).isEqualTo(inboundOrder.getBatchs().get(0).getAnnouncement().getId());
        assertThat(inboundOrderSaved.size()).isEqualTo(1);
    }

    @Test
    void save_throwException_whenSectionNotExists() {
        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        when(section.findById(anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, ()-> inboundOrderService.save(inboundOrderDTO));
    }

    @Test
    void save_throwException_whenAnnouncementNotExists() {
        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        when(announcement.findById(anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, ()-> inboundOrderService.save(inboundOrderDTO));
    }

    @Test
    void save_throwException_whenWarehouseNotExists() {
        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(100L);

        when(section.findById(anyLong())).thenReturn(getSection());
        when(announcement.findById(anyLong())).thenReturn(getAnnouncement());
        when(warehouse.findById(anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, ()-> inboundOrderService.save(inboundOrderDTO));
    }

    @Test
    void update_returnInboundOrder_whenSuccess() {
        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        when(repository.save(any(InboundOrder.class))).thenReturn(inboundOrder);
        when(repository.existsById(anyLong())).thenReturn(true);
        when(section.findById(anyLong())).thenReturn(getSection());
        when(announcement.findById(anyLong())).thenReturn(getAnnouncement());
        when(warehouse.findById(anyLong())).thenReturn(getWarehouse());

        List<BatchStockDTO> inboundOrderSaved = inboundOrderService.update(inboundOrder.getId(), inboundOrderDTO);

        assertThat(inboundOrderSaved.get(0).getProductId()).isEqualTo(inboundOrder.getBatchs().get(0).getAnnouncement().getId());
        assertThat(inboundOrderSaved.size()).isEqualTo(1);
    }

    @Test
    void update_ThrowException_whenIdNotExists() {
        InboundOrder inboundOrder = getInboundOrder();
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO(inboundOrder);
        inboundOrderDTO.setWarehouseId(1L);

        when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundException.class, ()-> inboundOrderService.update(inboundOrderDTO.getId(), inboundOrderDTO));
    }

}
