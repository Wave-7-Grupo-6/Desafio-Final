package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("annoucements")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "announcement")
    @JsonIgnoreProperties("annoucement")
    @JsonManagedReference
    private List<Batch> batchs;

    @OneToMany(mappedBy = "announcement")
    @JsonIgnoreProperties("annoucement")
    @JsonManagedReference
    @ToString.Exclude
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("announcements")
    private Seller seller;

    public Announcement(String description, BigDecimal price, Category category, Seller seller) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.seller = seller;
    }
}