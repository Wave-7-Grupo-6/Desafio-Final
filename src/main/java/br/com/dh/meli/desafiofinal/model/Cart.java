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

    @Column
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus cartStatus;

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
}
