package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.Annoucement;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductsController {
    @Autowired
    private IAnnouncement annService;

    @GetMapping("/list")
    public ResponseEntity<List<Annoucement>> findByCategory(@RequestParam String queryType){
        return new ResponseEntity<>(annService.findByCategory(queryType), HttpStatus.OK);
    }
}
