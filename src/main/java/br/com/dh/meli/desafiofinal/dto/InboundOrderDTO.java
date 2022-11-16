package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO {
    private Long id;
    private Long orderNumber;
    private LocalDate orderDate;

    @Positive(message = "Section id must be positive.")
    @NotNull(message = "Section id must not be null.")
    private Long sectionId;

    @Positive(message = "Warehouse id must be positive.")
    @NotNull(message = "Warehouse id must not be null.")
    private Long warehouseId;

    private List<@Valid BatchStockDTO> batchStockList;
}
