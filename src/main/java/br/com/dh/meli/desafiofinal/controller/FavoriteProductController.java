package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.FavoriteProductDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.service.IFavoriteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")

public class FavoriteProductController {

    @Autowired
    private IFavoriteList service;

    @PostMapping("/{clientId}/favorite-list")
    public ResponseEntity<Client> save(@PathVariable Long clientId,
                                                @RequestBody FavoriteProductDTO favoriteProductDTO){

        Client value = service.save(clientId, favoriteProductDTO.productId);

        return ResponseEntity.ok(value);
    }

    @DeleteMapping("/{clientId}/favorite-list")
    public ResponseEntity<Client> delete(@PathVariable Long clientId,
                                       @RequestBody FavoriteProductDTO favoriteProductDTO){

        Client value = service.delete(clientId, favoriteProductDTO.productId);

        return ResponseEntity.ok(value);
    }
}
