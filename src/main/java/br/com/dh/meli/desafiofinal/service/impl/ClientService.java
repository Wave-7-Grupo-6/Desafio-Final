package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.repository.ClientRepository;
import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.ICurrencyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Client service.
 */
@Service
@RequiredArgsConstructor
public class ClientService implements IClient {
    private final ClientRepository repo;

    private final ICurrencyApi currencyApiService;

    /**
     * Save a new client to the database.
     *
     * @param clientDTO This is the object that will be passed to the method.
     * @return A Client object
     */
    @Override
    public Client save(ClientDTO clientDTO) {
        return repo.save(new Client(null, clientDTO.getName(), null));
    }

    /**
     * Find all clients and return them as a list.
     *
     * @return A list of all the clients in the database.
     */
    @Override
    public List<Client> findAll() {
        return repo.findAll();
    }

    /**
     * Find all clients and return them as a list with converted currency.
     *
     * @param currencyName the currency
     * @return the client
     */
    @Override
    public List<Client> findAllAndCurrency(String currencyName) {
        List<Client> clients = repo.findAll();

        BigDecimal currencyValue = currencyApiService.getValue(currencyName);

        clients.forEach(client -> {
            client.getCarts().forEach(cart -> {
                cart.getCartItems().forEach(cartItem -> {
                    if (!cartItem.getCurrency().equalsIgnoreCase(currencyName)) {
                        cartItem.setCurrency(currencyName);
                        cartItem.setValue(cartItem.getValue().multiply(currencyValue));
                    }
                });
            });
        });

        return clients;
    }

    /**
     * Find a client by id, or throw an exception if it doesn't exist.
     *
     * @param id The id of the client to be found.
     * @return A client object
     */
    @Override
    public Client findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));
    }
}
