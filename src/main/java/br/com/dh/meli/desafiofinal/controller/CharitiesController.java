package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.AnnouncementDTO;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.service.ICharities;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
