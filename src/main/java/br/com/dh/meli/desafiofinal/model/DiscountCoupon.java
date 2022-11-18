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
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated discount coupon ID")
    private Long id;

    @ApiModelProperty(notes = "The discount coupon status")
    private int status;

    @ApiModelProperty(notes = "The discount coupon value")
    private Double discount;

    @ApiModelProperty(notes = "The discount coupon description")
    private String description;

    @ManyToMany(mappedBy = "discountCoupons", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("discountCoupons")
    @JsonBackReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The discount coupon categories")
    private List<Category> categories;

    @ManyToMany(mappedBy = "discountCoupons", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("discountCoupons")
    @JsonBackReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The discount coupon clients")
    private List<Client> clients;

    @OneToMany(mappedBy = "discountCoupon")
    @JsonIgnoreProperties("discountCoupon")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The discount coupon inbound orders")
    private List<PurchaseOrder> purchaseOrders;

    public DiscountCoupon(Long id, int status, Double discount, String description) {
        this.id = id;
        this.status = status;
        this.discount = discount;
        this.description = description;
    }
}
