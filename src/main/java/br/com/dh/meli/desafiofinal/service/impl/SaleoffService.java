package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.SaleoffDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import br.com.dh.meli.desafiofinal.service.ISaleoff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleoffService implements ISaleoff {
    @Autowired
    private BatchRepository repository;


    @Override
    public List<SaleoffDTO> getAll(){
        List<Batch> batchList = this.repository.findAll();

        return batchList
                .stream()
                .map(SaleoffDTO::new)
                .filter(sale -> sale.getDaysToExpire() < 21)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleoffDTO> getAllBySmallestPrice(){
        List<SaleoffDTO> saleoffList = this.getAll();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getSale_price));
        return saleoffList;
    }

    @Override
    public List<SaleoffDTO> getAllByBiggerDiscountPercentage(){
        List<SaleoffDTO> saleoffList = this.getAll();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getDiscountPercentage).reversed());
        return saleoffList;
    }

    @Override
    public List<SaleoffDTO> getAllByFewDaysToExpire(){
        List<SaleoffDTO> saleoffList = this.getAll();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getDaysToExpire));
        return saleoffList;
    }

    @Override
    public List<SaleoffDTO> getAllByBiggerPriceDifference(){
        List<SaleoffDTO> saleoffList = this.getAll();
        saleoffList.sort(Comparator.comparing(SaleoffDTO::getPrice_difference).reversed());
        return saleoffList;
    }
}
