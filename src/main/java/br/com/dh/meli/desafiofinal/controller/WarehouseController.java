package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.service.IWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    @Autowired
    private IWarehouse service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Warehouse> save(@RequestBody WarehouseDTO warehouse){
        Warehouse warehouseCreated = service.save(warehouse);

        return new ResponseEntity<>(warehouseCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
