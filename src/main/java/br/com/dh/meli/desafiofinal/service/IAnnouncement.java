package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ProductTypeDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;

import java.util.List;

import java.util.List;

public interface IAnnouncement {
    Announcement findById(Long id);
    Announcement save(Announcement announcement);
    List<Announcement> findAll();
    List<Announcement> findByCategory(String category);
    List<Announcement> findByProductType(Long prod_id);
    ProductTypeDTO findByProductTypeGroupByWarehouse(Long prod_id);
}
