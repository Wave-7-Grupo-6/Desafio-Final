package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.service.ISeller;
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
 * The type Seller controller.
 */
@RestController
@RequestMapping("/api/v1/seller")
@Api(tags = "Seller Controller", value = "SellerController", description = "Controller for Seller")
public class SellerController {
    @Autowired
    private ISeller service;

    /**
     * Save seller.
     *
     * @param sellerDTO the seller dto
     * @return the seller
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Seller")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Seller created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public Seller save(@RequestBody @Valid SellerDTO sellerDTO){
        return service.save(sellerDTO);
    }

    /**
     * Find all sellers.
     *
     * @return the response entity with sellers
     */
    @GetMapping
    @ApiOperation(value = "Get all Sellers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sellers found"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Seller>> findAll(){
        List<Seller> sellerList = service.findAll();
        if(sellerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    /**
     * Find seller by id.
     *
     * @param id the id
     * @return the response entity with seller
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Seller by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Seller found"),
            @ApiResponse(code = 404, message = "Seller not found"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Seller> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
