package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class InboundOrderService implements IInboundOrder{
     private final InboundOrderRepository repository;
     private final ISection section;
     private final IAnnouncement announcement;
     private final IBatch batch;

     @Override
     public List<BatchStockDTO> save(InboundOrderDTO inboundOrderDTO){
         InboundOrder inboundOrder = repository.save(createAtributes(inboundOrderDTO));

         return inboundOrder.getBatchs().stream().map(BatchStockDTO::new).collect(Collectors.toList());
     }

     @Override
     public InboundOrder findById(Long id) {
          return repository.findById(id).orElse(null);
     }

     private InboundOrder createAtributes(InboundOrderDTO inboundOrderDTO){
          InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, section.findById(inboundOrderDTO.getSectionId()));

          for(int i = 0; i < inboundOrderDTO.getBatchStockDTOList().size(); i++){
               BatchStockDTO batchStockDTO = inboundOrderDTO.getBatchStockDTOList().get(i);
               inboundOrder.getBatchs().add(batchStockDTO.createBatch(batchStockDTO, inboundOrder, announcement.findById(batchStockDTO.getProductId())));
          }

          return inboundOrder;
     }

     @Override
     public List<BatchStockDTO> update(Long id, InboundOrderDTO inboundOrderDTO){
          if(repository.existsById(id)){
               inboundOrderDTO.setId(id);
               return save(inboundOrderDTO);
          }
          throw new NotFoundException("Inbound Order not found.");
     }
}
