package br.com.dh.meli.desafiofinal.dto;


import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BatchStockDTO {
    private Long id;
    private Long productId;
    private Float currentTemperature;
    private int productQuantity;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private Float volume;
    private LocalDate dueDate;
    private BigDecimal price;


    public Batch createBatch(BatchStockDTO batchStockDTO, Announcement annoucement, InboundOrder inboundOrder){

        Batch batch = new Batch();
        batch.setId(batch.getId());
        batch.setCurrentTemperature(batchStockDTO.getCurrentTemperature());
        batch.setProductQuantity(batchStockDTO.getProductQuantity());
        batch.setManufacturingTime(batchStockDTO.getManufacturingTime());
        batch.setManufacturingDate(batchStockDTO.getManufacturingDate());
        batch.setVolume(batchStockDTO.getVolume());
        batch.setDueDate(batchStockDTO.getDueDate());
        batch.setAnnouncement(annoucement);
        batch.setInboundOrder(inboundOrder);
        batch.setSection(inboundOrder.getSection());
        return batch;
    }

}

