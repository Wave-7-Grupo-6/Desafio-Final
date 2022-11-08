package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "annoucement_id")
    @JsonIgnoreProperties("cartItems")
    @JsonBackReference
    private Annoucement annoucement;

    public void calculateValue(){
        value = annoucement.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
