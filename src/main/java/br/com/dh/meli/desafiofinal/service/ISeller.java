package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.model.Seller;

import java.util.List;

public interface ISeller {
    Seller findById(Long id);
    List<Seller> findAll();
    void save(SellerDTO sellerDTO);
}
