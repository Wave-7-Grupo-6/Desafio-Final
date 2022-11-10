package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
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
public class PurchaseOrderDTO {
    private LocalDate date;
    private Long buyerId; // client
    private OrderStatus orderStatus;
    private List<ProductDTO> products;
}
