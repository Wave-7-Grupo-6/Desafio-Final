package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.DiscountCouponDTO;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.model.DiscountCoupon;
import br.com.dh.meli.desafiofinal.repository.DiscountCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getCategory;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getDiscountCoupon;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCouponServiceTest {
    @Mock
    private DiscountCouponRepository repository;
    @Mock
    private ICategory category;
    private IDiscountCoupon discountCouponService;

    @BeforeEach
    void setUp() {
        discountCouponService = new DiscountCouponService(repository, category);
    }

    @Test
    void save_returnDiscountCouponDTO_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();
        DiscountCouponDTO couponDTO = new DiscountCouponDTO(coupon);

        when(repository.save(any())).thenReturn(coupon);

        DiscountCouponDTO couponSaved = discountCouponService.save(couponDTO);

        assertThat(couponSaved.getId()).isEqualTo(coupon.getId());
        assertThat(couponSaved.getDiscount()).isEqualTo(coupon.getDiscount());
    }

    @Test
    void update_returnDiscountCouponDTO_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();
        DiscountCouponDTO couponDTO = new DiscountCouponDTO(coupon);

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(coupon));
        when(repository.save(any())).thenReturn(coupon);

        DiscountCouponDTO couponUpdated = discountCouponService.update(couponDTO, coupon.getId());

        assertThat(couponUpdated.getId()).isEqualTo(coupon.getId());
        assertThat(couponUpdated.getDiscount()).isEqualTo(coupon.getDiscount());
    }

    @Test
    void update_throwException_whenDiscountCouponNotFound() {
        DiscountCoupon coupon = getDiscountCoupon();
        DiscountCouponDTO couponDTO = new DiscountCouponDTO(coupon);

        when(repository.findById(anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> discountCouponService.update(couponDTO, coupon.getId()));
    }

    @Test
    void delete_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(coupon));

        discountCouponService.delete(coupon.getId());

        assertThat(repository.existsById(coupon.getId())).isFalse();
    }

    @Test
    void delete_throwException_whenDiscountCouponNotFound() {
        DiscountCoupon coupon = getDiscountCoupon();

        when(repository.findById(anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> discountCouponService.delete(coupon.getId()));
    }

    @Test
    void findAll_returnListDiscountCoupon_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();
        List<DiscountCoupon> coupons = List.of(coupon);

        when(repository.findAll()).thenReturn(coupons);

        List<DiscountCouponDTO> couponsFound = discountCouponService.findAll();

        assertThat(couponsFound).isNotEmpty();
        assertThat(couponsFound).hasSize(1);
        assertThat(couponsFound.get(0).getId()).isEqualTo(coupon.getId());
        assertThat(couponsFound.get(0).getDiscount()).isEqualTo(coupon.getDiscount());
    }

    @Test
    void findById_returnDiscountCoupon_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(coupon));

        DiscountCouponDTO couponFound = discountCouponService.findById(coupon.getId());

        assertThat(couponFound.getId()).isEqualTo(coupon.getId());
        assertThat(couponFound.getDiscount()).isEqualTo(coupon.getDiscount());
    }

    @Test
    void findById_throwException_whenDiscountCouponNotFound() {
        DiscountCoupon coupon = getDiscountCoupon();

        when(repository.findById(anyLong())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> discountCouponService.findById(coupon.getId()));
    }

    @Test
    void findByDiscount_returnListDiscountCoupon_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();

        when(repository.findByDiscount(any())).thenReturn(java.util.List.of(coupon));

        List<DiscountCouponDTO> couponsFound = discountCouponService.findByDiscount(coupon.getDiscount());

        assertThat(couponsFound).isNotEmpty();
        assertThat(couponsFound.get(0).getId()).isEqualTo(coupon.getId());
        assertThat(couponsFound.get(0).getDiscount()).isEqualTo(coupon.getDiscount());
    }

    @Test
    void findByDiscount_returnEmptyList_whenDiscountCouponNotFound() {
        DiscountCoupon coupon = getDiscountCoupon();

        when(repository.findByDiscount(any())).thenReturn(java.util.List.of());

        List<DiscountCouponDTO> couponsFound = discountCouponService.findByDiscount(coupon.getDiscount());

        assertThat(couponsFound).isEmpty();
    }

    @Test
    void findByCategoryId_returnListDiscountCoupon_whenSuccess() {
        DiscountCoupon coupon = getDiscountCoupon();
        Category category = getCategory();

        when(this.category.findById(anyLong())).thenReturn(category);
        when(repository.findByCategoryId(anyLong())).thenReturn(java.util.List.of(coupon));

        List<DiscountCouponDTO> couponsFound = discountCouponService.findByCategoryId(category.getId());

        assertThat(couponsFound).isNotEmpty();
        assertThat(couponsFound.get(0).getId()).isEqualTo(coupon.getId());
        assertThat(couponsFound.get(0).getDiscount()).isEqualTo(coupon.getDiscount());
    }
}