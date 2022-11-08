package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "annoucement_id", nullable = false)
    private Annoucement annoucement;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    @JsonIgnoreProperties("purchaseItems")
    private PurchaseOrder purchaseOrder;
}