package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.*;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products")
@Api(tags = "Announcement Controller", value = "AnnouncementController", description = "Controller for Announcement")
public class AnnouncementController {

    @Autowired
    private IAnnouncement announcementService;
    @Autowired
    private ISeller sellerService;
    @Autowired
    private ICategory categoryService;
    @Autowired
    private IProductType productTypeService;
    @Autowired
    private IBatch batchService;

    @PostMapping
    @ApiOperation(value = "Create a new Announcement")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Announcement created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public ResponseEntity<AnnouncementDTO> save(@RequestBody AnnouncementDTO announcementDTO){
        Seller seller = sellerService.findById(announcementDTO.getSellerId());
        Category category = categoryService.findById(announcementDTO.getCategoryId());
        ProductType productType = productTypeService.findById(announcementDTO.getProductTypeId());
        Announcement announcement = new Announcement(null, announcementDTO.getDescription(),
                category, seller, productType);

        Announcement savedAnnouncement = announcementService.save(announcement);

        return new ResponseEntity<>(new AnnouncementDTO(savedAnnouncement), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Announcement by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Announcement found"),
            @ApiResponse(code = 404, message = "Announcement not found"),
    })
    public ResponseEntity<Announcement> findById(@PathVariable Long id){
        Announcement announcement = announcementService.findById(id);
        if(announcement !=null){
            return ResponseEntity.ok(announcement);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation(value = "Get all Announcements")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Announcements found"),
    })
    public ResponseEntity<List<Announcement>> findAll(){
        List<Announcement> announcements = announcementService.findAll();
        if(announcements.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(announcements);
    }

    @GetMapping(value = "/list", params = "category")
    @ApiOperation(value = "Get all Announcements by Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Announcements found"),
            @ApiResponse(code = 404, message = "Announcements not found"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<List<Announcement>> findByCategory(@RequestParam String category){
        return new ResponseEntity<>(announcementService.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping(value = "/list", params = "announcementId")
    @ApiOperation(value = "Get all Batches by Announcement ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Batches found"),
            @ApiResponse(code = 404, message = "Batches not found"),
            @ApiResponse(code = 404, message = "Announcement not found")
    })
    public AnnouncementStockDTO findStockByAnnouncement_Id(@RequestParam Long announcementId){
        Announcement announcement = announcementService.findById(announcementId);
        SectionDTO sectionDTO = new SectionDTO(announcement.getBatchs().get(0).getSection());
        List<BatchStockDTOResponse> batchStockDTOResponse = announcement.getBatchs().stream()
                .map(BatchStockDTOResponse::new)
                .collect(Collectors.toList());
        return new AnnouncementStockDTO(sectionDTO,announcementId, batchStockDTOResponse);
    }

    @GetMapping(value = "/list", params = {"announcementId", "orderBy"})
    @ApiOperation(value = "Get all Batches by Announcement ID and Order parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Batches found"),
            @ApiResponse(code = 404, message = "Batches not found"),
            @ApiResponse(code = 404, message = "Announcement not found")
    })
    public AnnouncementStockDTO findStockByAnnouncement_IdAndOrdered(@RequestParam Long announcementId, @RequestParam String orderBy){
        Announcement announcement = announcementService.findById(announcementId);
        SectionDTO sectionDTO = new SectionDTO(announcement.getBatchs().get(0).getSection());
        List<BatchStockDTOResponse> batchStockDTOResponse = announcement.getBatchs().stream()
                .map(BatchStockDTOResponse::new)
                .collect(Collectors.toList());

        if(Objects.equals(orderBy, "L")){
            batchStockDTOResponse.sort(Comparator.comparing(BatchStockDTOResponse::getId));
        } else if (Objects.equals(orderBy, "Q")) {
            batchStockDTOResponse.sort(Comparator.comparing(BatchStockDTOResponse::getProductQuantity));
        } else if(Objects.equals(orderBy, "V")){
            batchStockDTOResponse.sort(Comparator.comparing(BatchStockDTOResponse::getDueDate));
        }

        return new AnnouncementStockDTO(sectionDTO,announcementId, batchStockDTOResponse);
    }

    @GetMapping("/by_prod/{prod_id}")
    @ApiOperation(value = "Get all Announcements by Product Type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Announcements found"),
            @ApiResponse(code = 404, message = "Announcements not found"),
            @ApiResponse(code = 404, message = "Product Type not found")
    })
    public ResponseEntity<ProductTypeDTO> findByProductType(@PathVariable Long prod_id){
        return new ResponseEntity<>(announcementService.findByProductTypeGroupByWarehouse(prod_id), HttpStatus.OK);
    }

    @GetMapping(value = "/due-date", params = {"days", "section", "seller"})
    @ApiOperation(value = "Get all Batchs by due date, section and seller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Batchs found"),
            @ApiResponse(code = 404, message = "Batchs not found"),
            @ApiResponse(code = 404, message = "Section not found"),
            @ApiResponse(code = 404, message = "Seller not found")
    })
    public ResponseEntity<List<BatchDTO>> findByDueDate(@RequestParam Integer days, @RequestParam Long section, @RequestParam Long seller){
        return new ResponseEntity<>(batchService.findByDueDateIsBefore(days, section, seller), HttpStatus.OK);
    }
    @GetMapping(value = "/due-date/list", params = {"days", "category"})
    @ApiOperation(value = "Get all Batches by Category in Due Date Specific and Order Per DueDate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Batches found"),
            @ApiResponse(code = 404, message = "Batches not found"),
            @ApiResponse(code = 404, message = "Announcement not found")
    })
    public ResponseEntity<List<BatchDTO>> findStockByCategoryAndNumberDaysAndOrdered(@RequestParam Integer days, @RequestParam String category){
        List<Batch> batches = batchService.findByDaysAndCategoryAndOrderPerDueDate(days, category);
        System.out.println("------------------>");
        System.out.println(batches);
        List<BatchDTO> batchDTO = batches
                .stream()
                .map(BatchDTO::new)
                .collect(Collectors.toList());

        batchDTO.sort(Comparator.comparing(BatchDTO::getDueDate));

        return new ResponseEntity<>(batchDTO, HttpStatus.OK);
    }
}
