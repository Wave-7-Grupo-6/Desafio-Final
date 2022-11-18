package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTOResponse;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import br.com.dh.meli.desafiofinal.service.IPurchaseOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Purchase order controller.
 */
@RestController
@RequestMapping("/api/v1/purchase-order")
@Api(tags = "Purchase Order Controller", value = "PurchaseOrderController", description = "Controller for Purchase Order")
public class PurchaseOrderController {
    @Autowired
    private IPurchaseOrder service;

    /**
     * Save purchase order entity.
     *
     * @param purchaseOrderDTO the purchase order dto
     * @return the big decimal(total price)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Purchase Order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Purchase Order created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public BigDecimal save(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO){
        purchaseOrderDTO.setDate(LocalDate.now());
        return service.save(purchaseOrderDTO);
    }

    /**
     * Update purchase order status to delivered.
     *
     * @param id the id
     * @return the response entity with updated purchase order
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a status from Purchase Order by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Purchase Order updated successfully"),
            @ApiResponse(code = 404, message = "Purchase Order not found"),
    })
    public ResponseEntity<PurchaseOrder> updateStatusToDelivered(@PathVariable Long id){
        return new ResponseEntity<>(service.updateStatusToDelivered(id), HttpStatus.CREATED);
    }

    /**
     * Get purchase order by id.
     *
     * @param id the id
     * @return the response entity with purchase order.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Purchase Order by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Purchase Order found"),
            @ApiResponse(code = 404, message = "Purchase Order not found"),
    })
    public ResponseEntity<PurchaseOrderDTOResponse> getById(@PathVariable Long id){
        PurchaseOrderDTOResponse purchaseOrderDTOResponse = new PurchaseOrderDTOResponse(service.findById(id));
        return new ResponseEntity<>(purchaseOrderDTOResponse, HttpStatus.OK);
    }
}
