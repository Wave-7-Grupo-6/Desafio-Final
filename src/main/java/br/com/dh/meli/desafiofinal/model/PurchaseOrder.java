package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.ProductDTO;
import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public PurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Client client, List<Announcement> announcements) {
        this.date = purchaseOrderDTO.getDate();
        this.orderStatus = purchaseOrderDTO.getOrderStatus();
        this.client = client;

        this.purchaseItems = new HashSet<>();
        for(int i = 0; i < purchaseItems.size(); i++){
            this.purchaseItems.add(
                new PurchaseItem(purchaseOrderDTO.getProducts().get(i).getQuantity(), announcements.get(i), this)
            );
        }

        calculateTotalPrice(announcements, purchaseOrderDTO.getProducts());
    }

    private void calculateTotalPrice(List<Announcement> announcements, List<ProductDTO> productDTOS){
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for(int i = 0; i < announcements.size(); i++){
            BigDecimal value = new BigDecimal(String.valueOf(announcements.get(i).getPrice()
                    .multiply(new BigDecimal(productDTOS.get(i).getQuantity()))));
            total = total.add(value);
        }

        totalPrice = total;
    }
}
