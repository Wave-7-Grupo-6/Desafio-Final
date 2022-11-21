package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("carts")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "cart")
    @JsonIgnoreProperties("cart")
    @JsonManagedReference
    @ToString.Exclude
    private List<CartItem> cartItems;

    public BigDecimal getTotalPrice() {
        this.totalPrice = this.cartItems != null ? cartItems.stream()
                .map(item -> item.getValue().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add) : null;
        return this.totalPrice;
    }

}
