package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Transient
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "purchaseOrder")
    private Set<PurchaseItem> purchaseItems;
}
