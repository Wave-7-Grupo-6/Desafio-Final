package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    private LocalDate date;
    private Long buyerId; // client
    private OrderStatus orderStatus;
    private List<ProductDTO> products;

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.date = purchaseOrder.getDate();
        this.buyerId = purchaseOrder.getClient().getId();
        this.orderStatus = purchaseOrder.getOrderStatus();
        this.products = purchaseOrder.getPurchaseItems().stream()
                .map(item -> new ProductDTO(item.getAnnouncement().getId(), item.getQuantity()))
                .collect(Collectors.toList());
    }
}
