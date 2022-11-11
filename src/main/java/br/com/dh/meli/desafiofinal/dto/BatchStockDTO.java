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
    private Long batchNumber;
    private Long productId;
    private Float currentTemperature;
    private int productQuantity;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private Float volume;
    private LocalDate dueDate;
    private BigDecimal price;


    public Batch createBatch(BatchStockDTO batchStockDTO, InboundOrder inboundOrder, Announcement announcement){
        Batch batch = new Batch();
        batch.setBatchNumber(batchStockDTO.getBatchNumber());
        batch.setCurrentTemperature(batchStockDTO.getCurrentTemperature());
        batch.setProductQuantity(batchStockDTO.getProductQuantity());
        batch.setManufacturingTime(batchStockDTO.getManufacturingTime());
        batch.setManufacturingDate(batchStockDTO.getManufacturingDate());
        batch.setVolume(batchStockDTO.getVolume());
        batch.setDueDate(batchStockDTO.getDueDate());
        batch.setInboundOrder(inboundOrder);
        batch.setAnnouncement(announcement);
        batch.setSection(inboundOrder.getSection());
        return batch;
    }

    public BatchStockDTO(Batch batch) {
        this.batchNumber = batch.getBatchNumber();
        this.productId = batch.getAnnouncement().getId();
        this.currentTemperature = batch.getCurrentTemperature();
        this.productQuantity = batch.getProductQuantity();
        this.manufacturingDate = batch.getManufacturingDate();
        this.manufacturingTime = batch.getManufacturingTime();
        this.volume = batch.getVolume();
        this.dueDate = batch.getDueDate();
        this.price = batch.getAnnouncement().getPrice();
    }
}

