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

import java.util.List;

@RestController
@RequestMapping(("/api/v1/client"))
@Api(tags = "Client Controller", value = "ClientController", description = "Controller for Client")
public class ClientController {
    @Autowired
    private IClient service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Client created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public void save(@RequestBody ClientDTO clientDTO){
        service.save(clientDTO);
    }

    @GetMapping
    @ApiOperation(value = "Get all Clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clients found"),
    })
    public ResponseEntity<List<Client>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

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
