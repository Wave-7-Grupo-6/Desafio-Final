package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.SaleoffDTO;

import java.util.List;

public interface ISaleoff {
    List<SaleoffDTO> getAll();
    List<SaleoffDTO> getAllBySmallestPrice();
    List<SaleoffDTO> getAllByBiggerDiscountPercentage();
    List<SaleoffDTO> getAllByFewDaysToExpire();
    List<SaleoffDTO> getAllByBiggerPriceDifference();
}
