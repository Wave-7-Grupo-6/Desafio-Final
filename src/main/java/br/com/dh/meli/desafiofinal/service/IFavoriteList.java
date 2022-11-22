package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Client;

public interface IFavoriteList {

    Client save(Long clientId, Long productId);

    Client delete(Long clientId, Long productId);

}
