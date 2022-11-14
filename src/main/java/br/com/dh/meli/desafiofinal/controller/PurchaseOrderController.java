package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTOResponse;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import br.com.dh.meli.desafiofinal.service.IPurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/purchase-order")
public class PurchaseOrderController {
    @Autowired
    private IPurchaseOrder service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BigDecimal save(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO){
        purchaseOrderDTO.setDate(LocalDate.now());
        if(purchaseOrderDTO.getOrderStatus().toString() == "A"){
            throw new NotFoundException("Oi");
        }
        return service.save(purchaseOrderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updateStatusToDelivered(@PathVariable Long id){
        return new ResponseEntity<>(service.updateStatusToDelivered(id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTOResponse> getById(@PathVariable Long id){
        PurchaseOrderDTOResponse purchaseOrderDTOResponse = new PurchaseOrderDTOResponse(service.findById(id));
        return new ResponseEntity<>(purchaseOrderDTOResponse, HttpStatus.OK);
    }
}
