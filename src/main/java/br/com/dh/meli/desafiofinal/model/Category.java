package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated category ID")
    private Long id;

    @Column(length = 100, nullable = false)
    @ApiModelProperty(notes = "The category name")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The category temperature")
    private float temperature;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The category announcements")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The category sections")
    private List<Section> sections;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_discount_coupon",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_coupon_id")
    )
    @JsonIgnoreProperties("categories")
    @JsonBackReference
    private List<DiscountCoupon> discountCoupons;

    public Category(Long id, String name, float temperature) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
    }
}
