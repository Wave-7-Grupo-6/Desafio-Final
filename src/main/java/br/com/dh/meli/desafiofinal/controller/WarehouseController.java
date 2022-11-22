package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.WarehouseDTO;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.service.IWarehouse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Warehouse controller.
 */
@RestController
@RequestMapping("/api/v1/warehouse")
@Api(tags = "Warehouse Controller", value = "WarehouseController", description = "Controller for Warehouse")
public class WarehouseController {
    @Autowired
    private IWarehouse service;

    /**
     * Save warehouse.
     *
     * @param warehouse the warehouse
     * @return the response entity with warehouse
     */
    @PostMapping
    @ApiOperation(value = "Create a new Warehouse")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Warehouse created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Warehouse> save(@RequestBody @Valid WarehouseDTO warehouse){
        Warehouse warehouseCreated = service.save(warehouse);

        return new ResponseEntity<>(warehouseCreated, HttpStatus.CREATED);
    }

    /**
     * Get all warehouses.
     *
     * @return the response entity with warehouses
     */
    @GetMapping
    @ApiOperation(value = "Get all Warehouses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Warehouses found"),
    })
    public ResponseEntity<List<Warehouse>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    /**
     * Get warehouse by id.
     *
     * @param id the id
     * @return the response entity with warehouse
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Warehouse by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Warehouse found"),
            @ApiResponse(code = 404, message = "Warehouse not found"),
    })
    public ResponseEntity<Warehouse> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
