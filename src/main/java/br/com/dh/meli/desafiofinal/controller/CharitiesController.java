package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.service.ICharities;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charities")
public class CharitiesController {

    @Autowired
    private ICharities charityService;

    @PostMapping
    @ApiOperation(value = "Create a new Charity")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Announcement created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public ResponseEntity<Charities> save(@RequestBody Charities charity){
        Charities savedCharity = charityService.save(charity);
        return new ResponseEntity<>(savedCharity, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Get all charities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Charities found"),
            @ApiResponse(code = 404, message = "Charities not found"),
    })
    public ResponseEntity<List<Charities>> getAll(){
        List<Charities> charities = charityService.findAll();
        return new ResponseEntity<>(charities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a charity by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Charity found"),
            @ApiResponse(code = 404, message = "Charity not found"),
    })
    public ResponseEntity<Charities> getById(@PathVariable Long id){
        Charities charity = charityService.findById(id);
        return new ResponseEntity<>(charity, HttpStatus.OK);
    }
}
