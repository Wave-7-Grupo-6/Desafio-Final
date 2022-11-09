package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Annoucement;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDTO {
    private Long id;
    private Long productId;
    private Float currentTemperature;
    private int productQuantity;
    private LocalDate manufactoringDate;
    private LocalDateTime manufactoringTime;
    private Float volume;
    private LocalDate dueDate;
    private BigDecimal price;


    public Batch createBatch(Annoucement annoucement, InboundOrder inboundOrder){
        Batch batch = new Batch();
        batch.setId(id);
        batch.setCurrentTemperature(currentTemperature);
        batch.setProductQuantity(productQuantity);
        batch.setManufacturingTime(manufactoringTime);
        batch.setManufacturingDate(manufactoringDate);
        batch.setVolume(volume);
        batch.setDueDate(dueDate);
        batch.setAnnoucement(annoucement);
        batch.setInboundOrder(inboundOrder);
        return batch;
    }
}

