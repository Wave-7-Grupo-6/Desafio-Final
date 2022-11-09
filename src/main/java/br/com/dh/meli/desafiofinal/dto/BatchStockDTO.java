package br.com.dh.meli.desafiofinal.dto;

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
    private Long annoucementId;
    private Float currentTemperature;
    private int productQuantity;
    private LocalDate manufactoringDate;
    private LocalDateTime manufactoringTime;
    private Float volume;
    private LocalDate dueDate;
    private BigDecimal price;
}
