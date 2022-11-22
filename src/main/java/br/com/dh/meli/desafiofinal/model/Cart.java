package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The type Cart.
 */
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
    private User client;

    @OneToMany(mappedBy = "cart")
    @JsonIgnoreProperties("cart")
    @JsonManagedReference
    @ToString.Exclude
    private List<CartItem> cartItems;

    /**
     * It takes the cartItems, multiplies the value of each item by the quantity of that item, and then adds all of those
     * values together
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        this.totalPrice = this.cartItems != null ? cartItems.stream()
                .map(item -> item.getValue().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add) : null;
        return this.totalPrice;
    }

}
