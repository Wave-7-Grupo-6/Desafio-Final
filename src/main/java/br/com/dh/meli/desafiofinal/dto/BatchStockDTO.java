package br.com.dh.meli.desafiofinal.dto;


import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @Positive(message = "Product id must be positive.")
    @NotNull(message = "Product id must not be null.")
    private Long productId;
    private Float currentTemperature;
    @Positive(message = "Quantity must be positive.")
    @NotNull(message = "Quantity must not be null.")
    private int productQuantity;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;

    @NotNull(message = "Volume must not be null.")
    @Positive(message = "Volume must be positive.")
    private Float volume;

    @NotNull(message = "Due date must not be null.")
    @Future(message = "Due date must be in the future.")
    private LocalDate dueDate;

    @Positive(message = "Price must be positive.")
    @Digits(integer=4, fraction=2, message = "Price must not exceed 4 digits.")
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
        batch.setPrice(batchStockDTO.getPrice());
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
        this.price = batch.getPrice();
    }
}

