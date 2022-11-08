package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.AnnouncementDTO;
import br.com.dh.meli.desafiofinal.model.Annoucement;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.service.AnnouncementService;
import br.com.dh.meli.desafiofinal.service.CategoryService;
import br.com.dh.meli.desafiofinal.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private SellerService sellerService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Annoucement> save(@RequestBody AnnouncementDTO announcementDTO){
        Seller seller = sellerService.findById(announcementDTO.getSellerId());
        Category category = categoryService.findById(announcementDTO.getCategoryId());
        Annoucement annoucement = new Annoucement(null, announcementDTO.getDescription(),
                announcementDTO.getPrice(), category, seller);

        return new ResponseEntity<>(announcementService.save(annoucement), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Annoucement> findById(@PathVariable Long id){
        Annoucement annoucement = announcementService.findById(id);
        if(annoucement!=null){
            return ResponseEntity.ok(annoucement);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
