package br.com.dh.meli.desafiofinal.controller;


import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.service.IInboundOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
@Api(tags = "Inbound Order Controller", value = "InboundOrderController", description = "Controller for Inbound Order")
public class InboundOrderController {
    @Autowired
    private IInboundOrder service;

    @PostMapping
    @ApiOperation(value = "Create a new Inbound Order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inbound Order created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public ResponseEntity<List<BatchStockDTO>> saveOrder(@RequestBody @Valid InboundOrderDTO inboundOrderDTO){
        inboundOrderDTO.setOrderDate(LocalDate.now());
        return new ResponseEntity<>(service.save(inboundOrderDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a Inbound Order by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inbound Order updated successfully"),
            @ApiResponse(code = 404, message = "Inbound Order not found"),
    })
    public ResponseEntity<List<BatchStockDTO>> updateInboundOrder(@PathVariable Long id, @RequestBody @Valid InboundOrderDTO inboundOrderDTO){
        inboundOrderDTO.setOrderDate(LocalDate.now());
        List<BatchStockDTO> response = service.update(id, inboundOrderDTO);
        if (response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
