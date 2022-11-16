package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated purchase order ID")
    private Long id;

    @ApiModelProperty(notes = "The purchase order date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "The purchase order status")
    private OrderStatus orderStatus;

    @Transient
    @ApiModelProperty(notes = "The purchase order total value")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @ApiModelProperty(notes = "The purchase order client")
    private Client client;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("purchaseOrder")
    @ApiModelProperty(notes = "The purchase order items")
    private Set<PurchaseItem> purchaseItems;

    public PurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Client client) {
        this.date = purchaseOrderDTO.getDate();
        this.orderStatus = OrderStatus.valueOf(purchaseOrderDTO.getOrderStatus());
        this.client = client;
    }

    public BigDecimal getTotalPrice() {
        this.totalPrice = this.purchaseItems != null ? purchaseItems.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add) : null;
        return this.totalPrice;
    }
}
