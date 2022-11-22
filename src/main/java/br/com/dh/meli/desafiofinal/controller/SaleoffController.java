package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.SaleoffDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import br.com.dh.meli.desafiofinal.service.ISaleoff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saleoff")
public class SaleoffController {

    @Autowired
    private ISaleoff saleoffService;

    @GetMapping
    public ResponseEntity<List<SaleoffDTO>> getAll(){
        return new ResponseEntity<>(saleoffService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/smallest_price")
    public ResponseEntity<List<SaleoffDTO>> getAllBySmallestPrice(){
        return new ResponseEntity<>(saleoffService.getAllBySmallestPrice(), HttpStatus.OK);
    }

    @GetMapping("/bigger_discount")
    public ResponseEntity<List<SaleoffDTO>> getAllByBiggerDiscountPercentage(){
        return new ResponseEntity<>(saleoffService.getAllByBiggerDiscountPercentage(), HttpStatus.OK);
    }

    @GetMapping("/closer_to_expire")
    public ResponseEntity<List<SaleoffDTO>> getAllByFewDaysToExpire(){
        return new ResponseEntity<>(saleoffService.getAllByFewDaysToExpire(), HttpStatus.OK);
    }

    @GetMapping("/bigger_price_difference")
    public ResponseEntity<List<SaleoffDTO>> getAllByBiggerPriceDifference(){
        return new ResponseEntity<>(saleoffService.getAllByBiggerPriceDifference(), HttpStatus.OK);
    }
}
