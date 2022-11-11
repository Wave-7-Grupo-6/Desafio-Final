package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long sectionId;
    private Long warehouseId;
    private List<BatchStockDTO> batchStockDTOList;
}
