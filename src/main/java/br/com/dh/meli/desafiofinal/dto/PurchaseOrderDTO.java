package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.enums.validation.OrderStatusValue;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    private LocalDate date;

    @Positive(message = "Buyer id must be positive.")
    @NotNull(message = "Buyer id must not be null.")
    private Long buyerId; // client

    @OrderStatusValue(enumClass = OrderStatus.class)
    private String orderStatus;
    private List<@Valid ProductDTO> products;

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.date = purchaseOrder.getDate();
        this.buyerId = purchaseOrder.getClient().getId();
        this.orderStatus = purchaseOrder.getOrderStatus().toString();
        this.products = purchaseOrder.getPurchaseItems().stream()
                .map(item -> new ProductDTO(item.getAnnouncement().getId(), item.getQuantity(), null))
                .collect(Collectors.toList());
    }
}
