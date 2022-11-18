package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.DiscountCouponDTO;
import br.com.dh.meli.desafiofinal.model.DiscountCoupon;
import br.com.dh.meli.desafiofinal.service.ICategory;
import br.com.dh.meli.desafiofinal.service.IDiscountCoupon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.NoSuchElementException;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getCategory;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getDiscountCoupon;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiscountCouponController.class)
class DiscountCouponControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IDiscountCoupon discountCouponService;

    @MockBean
    private ICategory categoryService;

    @Test
    void save_returnDiscountCouponDTOAndCreatedStatus_whenSuccess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();
        when(categoryService.findById(anyLong())).thenReturn(getCategory());
        when(discountCouponService.save(any())).thenReturn(new DiscountCouponDTO(discountCoupon));

        DiscountCouponDTO discountCouponDTO = new DiscountCouponDTO(discountCoupon);

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/discount-coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(discountCouponDTO))
        );

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(discountCoupon.getId()))
                .andExpect(jsonPath("$.discount").value(discountCoupon.getDiscount()));
    }

    @Test
    void findAll_returnListDiscountCoupon_whenSuccess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();
        when(discountCouponService.findAll()).thenReturn(List.of(new DiscountCouponDTO(discountCoupon)));

        mockMvc.perform(
                get("/api/v1/discount-coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(discountCoupon.getId()))
                .andExpect(jsonPath("$[0].discount").value(discountCoupon.getDiscount()));
    }

    @Test
    void update_returnDiscountCoupon_whenSuccess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();
        when(categoryService.findById(anyLong())).thenReturn(getCategory());
        when(discountCouponService.update(any(), anyLong())).thenReturn(new DiscountCouponDTO(discountCoupon));

        mockMvc.perform(
                put("/api/v1/discount-coupons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new DiscountCouponDTO(discountCoupon)))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(discountCoupon.getId()))
                .andExpect(jsonPath("$.discount").value(discountCoupon.getDiscount()));
    }

    @Test
    void update_throwNoSuchElementException_whenDiscountCouponNotFound() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();
        when(categoryService.findById(anyLong())).thenReturn(getCategory());
        when(discountCouponService.update(any(), anyLong())).thenThrow(NoSuchElementException.class);

        mockMvc.perform(
                put("/api/v1/discount-coupons/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new DiscountCouponDTO(discountCoupon)))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_whenSuccess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();
        when(discountCouponService.findById(anyLong())).thenReturn(new DiscountCouponDTO(discountCoupon));

        mockMvc.perform(
                delete("/api/v1/discount-coupons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void findById_returnDiscountCoupon_whenSuccess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();

        when(discountCouponService.findById(anyLong())).thenReturn(new DiscountCouponDTO(discountCoupon));

        mockMvc.perform(
                get("/api/v1/discount-coupons/{id}", discountCoupon.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(discountCoupon))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(discountCoupon.getId()))
                .andExpect(jsonPath("$.discount").value(discountCoupon.getDiscount()));
    }

    @Test
    void findById_returnNotFound_whenDiscountCouponNotFound() throws Exception {
        when(discountCouponService.findById(anyLong())).thenThrow(NoSuchElementException.class);

        mockMvc.perform(
                get("/api/v1/discount-coupons/{id}", 100L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound());
    }

    @Test
    void findByDiscount_returnListDiscountCoupon_whenSucess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();

        when(discountCouponService.findByDiscount(any())).thenReturn(List.of(new DiscountCouponDTO(discountCoupon)));

        mockMvc.perform(
                get("/api/v1/discount-coupons/discount/{discount}", discountCoupon.getDiscount())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(discountCoupon.getId()))
                .andExpect(jsonPath("$[0].discount").value(discountCoupon.getDiscount()));
    }

    @Test
    void findByDiscount_returnListEmpty_whenDiscountCouponNotFound() throws Exception {
        when(discountCouponService.findByDiscount(any())).thenReturn(List.of());

        mockMvc.perform(
                get("/api/v1/discount-coupons/discount/{discount}", 100L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void findByCategoryId_returnListDiscountCoupon_whenSucess() throws Exception {
        DiscountCoupon discountCoupon = getDiscountCoupon();

        when(discountCouponService.findByCategoryId(anyLong())).thenReturn(List.of(new DiscountCouponDTO(discountCoupon)));

        mockMvc.perform(
                get("/api/v1/discount-coupons/category/{id}", getCategory().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(discountCoupon.getId()))
                .andExpect(jsonPath("$[0].discount").value(discountCoupon.getDiscount()));
    }

    @Test
    void findByCategoryId_returnListEmpty_whenDiscountCouponNotFound() throws Exception {
        when(discountCouponService.findByCategoryId(anyLong())).thenReturn(List.of());

        mockMvc.perform(
                get("/api/v1/discount-coupons/category/{id}", 100L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void findByCategoryId_throwException_whenCategoryIdNotFound() throws Exception {
        when(discountCouponService.findByCategoryId(anyLong())).thenThrow(NoSuchElementException.class);

        mockMvc.perform(
                get("/api/v1/discount-coupons/category/{id}", 100L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound());
    }
}