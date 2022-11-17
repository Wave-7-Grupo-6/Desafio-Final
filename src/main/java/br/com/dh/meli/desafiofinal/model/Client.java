package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated client ID")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The client name")
    private String name;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The client carts")
    private List<Cart> carts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "client_discount_coupon",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_coupon_id")
    )
    private List<DiscountCoupon> discountCoupons;
}
