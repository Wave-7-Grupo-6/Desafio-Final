package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The database generated announcement ID")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The announcement description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("annoucements")
    @JsonBackReference
    @ApiModelProperty(notes = "The announcement category")
    private Category category;

    @OneToMany(mappedBy = "announcement")
    @JsonIgnoreProperties("annoucement")
    @JsonManagedReference
    @ApiModelProperty(notes = "The announcement batchs")
    private List<Batch> batchs;

    @OneToMany(mappedBy = "announcement")
    @JsonIgnoreProperties("annoucement")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The announcement cart items")
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("announcements")
    @ApiModelProperty(notes = "The announcement seller")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    @JsonIgnoreProperties("announcements")
    @ApiModelProperty(notes = "The announcement product type")
    private ProductType productType;

    /**
     * Instantiates a new Announcement.
     *
     * @param id          the id
     * @param description the description
     * @param category    the category
     * @param seller      the seller
     * @param productType the product type
     */
    public Announcement(Long id, String description, Category category, Seller seller, ProductType productType) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.seller = seller;
        this.productType = productType;
    }
}
