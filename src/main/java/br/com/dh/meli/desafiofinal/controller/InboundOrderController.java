package br.com.dh.meli.desafiofinal.controller;


import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.InboundOrder;
import br.com.dh.meli.desafiofinal.service.IInboundOrder;
import br.com.dh.meli.desafiofinal.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {

    @Autowired
    private IInboundOrder service;

    public ResponseEntity<InboundOrder> saveOrder(@RequestBody InboundOrderDTO inboundOrderDTO){
        return new ResponseEntity<>(service.save(inboundOrderDTO), HttpStatus.CREATED);
    }
}
