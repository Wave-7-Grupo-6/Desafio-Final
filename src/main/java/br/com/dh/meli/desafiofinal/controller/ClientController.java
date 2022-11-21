package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.service.IClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Client controller.
 */
@RestController
@RequestMapping(("/api/v1/client"))
@Api(tags = "Client Controller", value = "ClientController", description = "Controller for Client")
public class ClientController {
    @Autowired
    private IClient service;

    /**
     * Save client.
     *
     * @param clientDTO the client dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Client created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public void save(@RequestBody @Valid ClientDTO clientDTO){
        service.save(clientDTO);
    }

    /**
     * Find all response entity with clients.
     *
     * @return the response entity with clients
     */
    @GetMapping
    @ApiOperation(value = "Get all Clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clients found"),
    })
    public ResponseEntity<List<Client>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    /**
     * Find all response entity with clients and converted currency.
     * @param currency the currency
     * @return the response entity with clients
     */
    @GetMapping(params = "currency")
    @ApiOperation(value = "Get all Clients and converted currency")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clients found"),
            @ApiResponse(code = 404, message = "Currency not found"),
    })
    public ResponseEntity<List<Client>> findAllAndCurrency(@RequestParam String currency){
        return new ResponseEntity<>(service.findAllAndCurrency(currency), HttpStatus.OK);
    }

    /**
     * Find by id response entity with client.
     *
     * @param id the id
     * @return the response entity with client
     */
    @GetMapping("{id}")
    @ApiOperation(value = "Get a Client by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client found"),
            @ApiResponse(code = 404, message = "Client not found"),
    })
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
