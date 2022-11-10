package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.AnnouncementDTO;
import br.com.dh.meli.desafiofinal.dto.BatchStockDTOResponse;
import br.com.dh.meli.desafiofinal.dto.SectionDTO;
import br.com.dh.meli.desafiofinal.dto.AnnouncementStockDTO;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.ICategory;
import br.com.dh.meli.desafiofinal.service.ISection;
import br.com.dh.meli.desafiofinal.service.ISeller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class AnnouncementController {

    @Autowired
    private IAnnouncement announcementService;
    @Autowired
    private ISeller sellerService;
    @Autowired
    private ICategory categoryService;

    @PostMapping
    public ResponseEntity<AnnouncementDTO> save(@RequestBody AnnouncementDTO announcementDTO){
        Seller seller = sellerService.findById(announcementDTO.getSellerId());
        Category category = categoryService.findById(announcementDTO.getCategoryId());
        Announcement announcement = new Announcement(null, announcementDTO.getDescription(),
                announcementDTO.getPrice(), category, seller);

        Announcement savedAnnouncement = announcementService.save(announcement);

        return new ResponseEntity<>(new AnnouncementDTO(savedAnnouncement), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id){
        Announcement announcement = announcementService.findById(id);
        if(announcement !=null){
            return ResponseEntity.ok(announcement);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Announcement>> findAll(){
        List<Announcement> announcements = announcementService.findAll();
        if(announcements.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(announcements);
    }

    @GetMapping(value = "/list", params = "category")
    public ResponseEntity<List<Announcement>> findByCategory(@RequestParam String category){
        return new ResponseEntity<>(announcementService.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping(value = "/list", params = "announcementId")
    public AnnouncementStockDTO findStockByAnnouncement_Id(@RequestParam Long announcementId){
        Announcement announcement = announcementService.findById(announcementId);
        SectionDTO sectionDTO = new SectionDTO(announcement.getBatchs().get(0).getSection());
        List<BatchStockDTOResponse> batchStockDTOResponse = announcement.getBatchs().stream()
                .map(BatchStockDTOResponse::new)
                .collect(Collectors.toList());
        return new AnnouncementStockDTO(sectionDTO,announcementId, batchStockDTOResponse);
    }
}
