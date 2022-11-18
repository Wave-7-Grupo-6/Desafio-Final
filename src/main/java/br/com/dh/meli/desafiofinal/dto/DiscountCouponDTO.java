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
        this.id = discountCoupon.getId();
        this.status = 1;
        if(discountCoupon.getStatus() != 0) this.status = discountCoupon.getStatus();
        this.discount = discountCoupon.getDiscount();
        this.description = discountCoupon.getDescription();
    }

    public DiscountCoupon toDiscountCoupon() {
        return new DiscountCoupon(this.id, this.status, this.discount, this.description);
    }
}
