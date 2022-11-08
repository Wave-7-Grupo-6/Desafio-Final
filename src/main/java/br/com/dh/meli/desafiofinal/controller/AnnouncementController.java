package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.Annoucement;
import br.com.dh.meli.desafiofinal.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    @GetMapping("/{id}")
    public ResponseEntity<Annoucement> findById(@PathVariable Long id){
        Annoucement annoucement = service.findById(id);
        if(annoucement!=null){
            return ResponseEntity.ok(annoucement);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
