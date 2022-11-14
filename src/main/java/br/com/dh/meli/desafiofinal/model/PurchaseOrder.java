package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Transient
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("purchaseOrder")
    private Set<PurchaseItem> purchaseItems;

    public PurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Client client) {
        this.date = purchaseOrderDTO.getDate();
        this.orderStatus = purchaseOrderDTO.getOrderStatus();
        this.client = client;
    }

    public BigDecimal getTotalPrice() {
        this.totalPrice = this.purchaseItems != null ? purchaseItems.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add) : null;
        return this.totalPrice;
    }
}
