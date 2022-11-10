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
public class BatchStockDTOResponse {
    private Long id;
    private int productQuantity;
    private LocalDate dueDate;

    public BatchStockDTOResponse(Batch batch) {
        this.id = batch.getId();
        this.productQuantity = batch.getProductQuantity();
        this.dueDate = batch.getDueDate();
    }
}
