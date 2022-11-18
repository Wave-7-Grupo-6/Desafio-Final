package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Inbound order dto.
 */
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

    /**
     * Instantiates a new Inbound order dto.
     *
     * @param inboundOrder the inbound order
     */
    public InboundOrderDTO(InboundOrder inboundOrder){
        this.id = inboundOrder.getId();
        this.orderNumber = inboundOrder.getOrderNumber();
        this.orderDate = inboundOrder.getOrderDate();
        this.sectionId = inboundOrder.getSection().getId();
        List<BatchStockDTO> list = new ArrayList<>();
        for (Batch batch:
             inboundOrder.getBatchs()) {
            list.add(new BatchStockDTO(batch));
        }
        this.batchStockList = list;
    }
}
