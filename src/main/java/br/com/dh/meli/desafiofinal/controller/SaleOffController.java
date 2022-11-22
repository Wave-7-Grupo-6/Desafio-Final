package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.SaleoffDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/saleoff")
public class SaleOffController {

    @Autowired
    private BatchRepository batchRepository;

    public List<SaleoffDTO> getUnfilteredSaleoff (){
        List<Batch> batchList = batchRepository.findAll();

        return batchList
                .stream()
                .map(SaleoffDTO::new)
                .filter(sale -> sale.getDaysToExpire() < 21)
                .collect(Collectors.toList());
    }

    @GetMapping
    public ResponseEntity<List<SaleoffDTO>> getAll(){
        return new ResponseEntity<>(getUnfilteredSaleoff(), HttpStatus.OK);
    }

    @GetMapping("/smallest_price")
    public ResponseEntity<List<SaleoffDTO>> getBySmallestPrice(){
        List<SaleoffDTO> saleoffList = getUnfilteredSaleoff();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getSale_price));
        return new ResponseEntity<>(saleoffList, HttpStatus.OK);
    }

    @GetMapping("/bigger_discount")
    public ResponseEntity<List<SaleoffDTO>> getByBiggerDiscountPercentage(){
        List<SaleoffDTO> saleoffList = getUnfilteredSaleoff();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getDiscountPercentage).reversed());
        return new ResponseEntity<>(saleoffList, HttpStatus.OK);
    }

    @GetMapping("/closer_to_expire")
    public ResponseEntity<List<SaleoffDTO>> getByFewDaysToExpire(){
        List<SaleoffDTO> saleoffList = getUnfilteredSaleoff();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getDaysToExpire));
        return new ResponseEntity<>(saleoffList, HttpStatus.OK);
    }

    @GetMapping("/bigger_price_difference")
    public ResponseEntity<List<SaleoffDTO>> getByBiggerPriceDifference(){
        List<SaleoffDTO> saleoffList = getUnfilteredSaleoff();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getPrice_difference).reversed());
        return new ResponseEntity<>(saleoffList, HttpStatus.OK);
    }
}
