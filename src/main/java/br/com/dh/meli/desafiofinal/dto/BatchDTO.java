package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
    private Long id;
    private int productQuantity;
    private LocalDate dueDate;
    private Long productId;
    private Long productTypeId;

    public BatchDTO(Batch batch) {
        this.id = batch.getBatchNumber();
        this.productQuantity = batch.getProductQuantity();
        this.dueDate = batch.getDueDate();
        this.productId = batch.getAnnouncement().getId();
        this.productTypeId = batch.getAnnouncement().getProductType().getId();
    }
}
