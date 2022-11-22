package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.IFavoriteList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteListService implements IFavoriteList {

    private final IClient clientService;
    private final IAnnouncement announcementService;

    @Override
    public Client save(Long clientId, Long productId) {

        //ObtemCliente
        Client client = clientService.findById(clientId);
        if(client == null)
            return null;

        //ObtemProduto
        Announcement announcement = announcementService.findById(productId);
        if(announcement == null)
            return null;

        //Verificar se o produto já existe na lista de favoritos do cliente
        if(client.getFavorites()!= null && client.getFavorites().stream().anyMatch(a-> a.getId().equals(productId)))
            return null;

        client.getFavorites().add(announcement);

        return clientService.save(client);
    }

    public Client delete(Long clientId, Long productId){

        Client client = clientService.findById(clientId);
        if(client == null)
            return null;

        Announcement announcement = announcementService.findById(productId);
        if(announcement == null)
            return null;

        if(client.getFavorites() == null || client.getFavorites().stream().noneMatch(a-> a.getId().equals(productId)))
            return null;

        client.getFavorites().removeIf(x->x.getId().equals(productId));

        return clientService.save(client);
    }

}
