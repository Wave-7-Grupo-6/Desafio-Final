package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientFavoritesDTO;
import br.com.dh.meli.desafiofinal.model.Client;

public interface IFavoriteList {

    ClientFavoritesDTO save(Long clientId, Long productId);

    ClientFavoritesDTO delete(Long clientId, Long productId);

}
