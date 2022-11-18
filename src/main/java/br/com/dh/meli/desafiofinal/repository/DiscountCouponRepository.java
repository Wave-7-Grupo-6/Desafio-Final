package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long> {
    List<DiscountCoupon> findByDiscount(Double discount);

    @Query("select c.discountCoupons from Category c where c.id = :categoryId")
    List<DiscountCoupon> findByCategoryId(Long categoryId);
}
