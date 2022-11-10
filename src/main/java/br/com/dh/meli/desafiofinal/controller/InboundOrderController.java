package br.com.dh.meli.desafiofinal.controller;


import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.service.IInboundOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {

    @Autowired
    private IInboundOrder service;

    @PostMapping
    public ResponseEntity<List<Batch>> saveOrder(@RequestBody InboundOrderDTO inboundOrderDTO){
        return new ResponseEntity<>(service.save(inboundOrderDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Batch>> updateInboundOrder(@PathVariable Long id, @RequestBody InboundOrderDTO inboundOrderDTO){
        List<Batch> response = service.update(id, inboundOrderDTO);
        if (response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
