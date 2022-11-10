package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import br.com.dh.meli.desafiofinal.service.IPurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/purchase-order")
public class PurchaseOrderController {
    @Autowired
    private IPurchaseOrder service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BigDecimal save(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return service.save(purchaseOrderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(@PathVariable Long id){
        return new ResponseEntity<>(service.update(id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
