package br.com.dh.meli.desafiofinal.model;

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

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("purchaseOrder")
    private Set<PurchaseItem> purchaseItems;
}
