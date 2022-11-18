package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.DiscountCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCouponDTO {
    private Long id;
    private int status;
    private Double discount;
    private String description;

    public DiscountCouponDTO(DiscountCoupon discountCoupon) {
        this.status = discountCoupon.getStatus();
        this.discount = discountCoupon.getDiscount();
        this.description = discountCoupon.getDescription();
    }

    public DiscountCoupon toDiscountCoupon() {
        return new DiscountCoupon(this.id, this.status, this.discount, this.description);
    }

    public DiscountCoupon toDiscountCoupon(Long id) {
        return new DiscountCoupon(id, this.status, this.discount, this.description);
    }
}
