package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.DiscountCouponDTO;

import java.util.List;

public interface IDiscountCoupon {
    void save(DiscountCouponDTO discountCouponDTO);
    void update(DiscountCouponDTO discountCouponDTO, Long id);
    void delete(Long id);

    List<DiscountCouponDTO> findAll();
    DiscountCouponDTO findById(Long id);
    List<DiscountCouponDTO> findByDiscount(Double discount);
    List<DiscountCouponDTO> findByCategoryId(Long categoryId);
}
