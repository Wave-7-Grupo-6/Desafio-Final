package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.DiscountCouponDTO;
import br.com.dh.meli.desafiofinal.model.DiscountCoupon;
import br.com.dh.meli.desafiofinal.service.IDiscountCoupon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount-coupons")
@Api(tags = "Discount Coupon Controller", value = "DiscountCouponController", description = "Controller for Discount Coupon")
public class DiscountCouponController {
    @Autowired
    private IDiscountCoupon service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Discount Coupon")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Discount Coupon created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public DiscountCouponDTO save(DiscountCouponDTO discountCouponDTO){
        return service.save(discountCouponDTO);
    }

    @GetMapping
    @ApiOperation(value = "Get all Discount Coupons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Discount Coupons found"),
    })
    public ResponseEntity<List<DiscountCouponDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
