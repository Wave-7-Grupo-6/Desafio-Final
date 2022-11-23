package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.dto.ClientFavoritesDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FavoriteListServiceTest {

    @Mock
    private IClient clientService;

    @Mock
    private IAnnouncement announcementService;

    private FavoriteListService service;

    @BeforeEach
    void setup() {
        service = new FavoriteListService(clientService, announcementService);
    }

    @Test
    void save_returnFavoriteList_whenAddNewFavoriteAnnouncement() {
        //arrange
        Long clientId = 1L;
        Long productId = 1L;

        Client client = new Client(clientId, "TestClient", null, new ArrayList<>());
        when(clientService.findById(clientId)).thenReturn(client);

        Announcement announcement = new Announcement(productId, "AnnouncementTest", new Category(1L, "CategoryTest", 1L), null, null);
        when(announcementService.findById(productId)).thenReturn(announcement);

        Client clientAltered = new Client(clientId, "TestClient", null, null);
        List<Announcement> favorites = new ArrayList<>();
        favorites.add(announcement);
        clientAltered.setFavorites(favorites);
        when(clientService.save(any(Client.class))).thenReturn(clientAltered);

        //act
        ClientFavoritesDTO actual = service.save(clientId, productId);

        //assert
        assertThat(actual.getLista()).isNotEmpty();
    }

    @Test
    void save_returnNull_whenClientDoesNotExists() {
        Long clientId = 1L;
        Long productId = 1L;

        ClientFavoritesDTO actual = service.save(clientId, productId);

        assertThat(actual).isNull();
    }

    @Test
    void save_returnNull_whenAnnouncementDoesNotExists() {
        Long clientId = 1L;
        Long productId = 1L;

        Client client = new Client(clientId, "TestClient", null, new ArrayList<>());
        when(clientService.findById(clientId)).thenReturn(client);

        ClientFavoritesDTO actual = service.save(clientId, productId);

        assertThat(actual).isNull();
    }

    @Test
    void save_returnNull_whenAnnouncementAlreadyIsFavorited() {
        Long clientId = 1L;
        Long productId = 1L;

        Client client = new Client(clientId, "TestClient", null, new ArrayList<>());
        Announcement announcement = new Announcement(productId, "AnnouncementTest", new Category(1L, "CategoryTest", 1L), null, null);
        client.getFavorites().add(announcement);
        when(clientService.findById(clientId)).thenReturn(client);

        when(announcementService.findById(productId)).thenReturn(announcement);


        ClientFavoritesDTO actual = service.save(clientId, productId);

        assertThat(actual).isNull();

    }

    @Test
    void delete_returnEmptyFavoriteList_whenRemovingLastFavoriteItem() {
        //arrange
        Long clientId = 1L;
        Long productId = 1L;

        Client client = new Client(clientId, "TestClient", null, new ArrayList<>());
        Announcement announcement = new Announcement(productId, "AnnouncementTest", new Category(1L, "CategoryTest", 1L), null, null);
        client.getFavorites().add(announcement);
        when(clientService.findById(clientId)).thenReturn(client);

        when(announcementService.findById(productId)).thenReturn(announcement);

        Client clientAltered = new Client(clientId, "TestClient", null, new ArrayList<>());
        when(clientService.save(any(Client.class))).thenReturn(clientAltered);

        ClientFavoritesDTO actual = service.delete(clientId, productId);

        assertThat(actual.getLista()).isEmpty();

    }

    @Test
    void delete_returnNull_whenClientNotExist() {

        Long clientId = 1L;
        Long productId = 1L;

        ClientFavoritesDTO actual = service.delete(clientId, productId);

        assertThat(actual).isNull();

    }

    @Test
    void delete_returnNull_whenAnnouncementNotExist() {

        Long clientId = 1L;
        Long productId = 1L;

        Client client = new Client(clientId, "TestClient", null, new ArrayList<>());
        when(clientService.findById(clientId)).thenReturn(client);

        ClientFavoritesDTO actual = service.delete(clientId, productId);

        assertThat(actual).isNull();

    }

    @Test
    void delete_returnNull_whenAnnouncementNotExistInFavoriteList() {
        Long clientId = 1L;
        Long productId = 1L;

        Client client = new Client(clientId, "TestClient", null, new ArrayList<>());
        Announcement announcement = new Announcement(productId, "AnnouncementTest", new Category(1L, "CategoryTest", 1L), null, null);
        when(clientService.findById(clientId)).thenReturn(client);

        when(announcementService.findById(productId)).thenReturn(announcement);

        ClientFavoritesDTO actual = service.delete(clientId, productId);

        assertThat(actual).isNull();

    }
}
