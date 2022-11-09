package br.com.dh.meli.desafiofinal.controller;


import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.service.IInboundOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {

    @Autowired
    private IInboundOrder service;

    @PostMapping
    public ResponseEntity<List<Batch>> saveOrder(@RequestBody InboundOrderDTO inboundOrderDTO){
        return new ResponseEntity<>(service.save(inboundOrderDTO), HttpStatus.CREATED);
    }
}
