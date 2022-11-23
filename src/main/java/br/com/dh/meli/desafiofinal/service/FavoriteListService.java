package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.dto.ClientFavoritesDTO;
import br.com.dh.meli.desafiofinal.dto.FavoriteAnnouncementDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.IFavoriteList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FavoriteListService implements IFavoriteList {

    private final IClient clientService;
    private final IAnnouncement announcementService;

    @Override
    public ClientFavoritesDTO save(Long clientId, Long productId) {

        //ObtemCliente
        Client client = clientService.findById(clientId);
        if(client == null)
            return null;

        //ObtemProduto
        Announcement announcement = announcementService.findById(productId);
        if(announcement == null)
            return null;

        //Verificar se o produto já existe na lista de favoritos do cliente
        if(client.getFavorites().stream().anyMatch(a-> a.getId().equals(productId)))
            return null;

        client.getFavorites().add(announcement);


        Client valor= clientService.save(client);

        ClientFavoritesDTO objeto  = new ClientFavoritesDTO();
        objeto.setClientId(valor.getId());
        objeto.setClientName(valor.getName());

        List<FavoriteAnnouncementDTO> favoritesDtos = new ArrayList<>();
        for (Announcement a: valor.getFavorites()
        ) {
            FavoriteAnnouncementDTO favorite = new FavoriteAnnouncementDTO();
            favorite.setId(a.getId());
            favorite.setDescription(a.getDescription());
            favorite.setCategoryId(a.getCategory().getId());
            favorite.setCategoryName(a.getCategory().getName());
            favoritesDtos.add(favorite);
        }

        objeto.setLista(favoritesDtos);
        return objeto;
    }

    public ClientFavoritesDTO delete(Long clientId, Long productId){

        Client client = clientService.findById(clientId);
        if(client == null)
            return null;

        Announcement announcement = announcementService.findById(productId);
        if(announcement == null)
            return null;

        if(client.getFavorites().stream().noneMatch(a-> a.getId().equals(productId)))
            return null;

        client.getFavorites().removeIf(x->x.getId().equals(productId));

        Client valor= clientService.save(client);

        ClientFavoritesDTO objeto  = new ClientFavoritesDTO();
        objeto.setClientId(valor.getId());
        objeto.setClientName(valor.getName());

        List<FavoriteAnnouncementDTO> favoritesDtos = new ArrayList<>();
        for (Announcement a: valor.getFavorites()
             ) {
            FavoriteAnnouncementDTO favorite = new FavoriteAnnouncementDTO();
            favorite.setId(a.getId());
            favorite.setDescription(a.getDescription());
            favorite.setCategoryId(a.getCategory().getId());
            favorite.setCategoryName(a.getCategory().getName());
            favoritesDtos.add(favorite);
        }

        objeto.setLista(favoritesDtos);
        return objeto;
    }

}
