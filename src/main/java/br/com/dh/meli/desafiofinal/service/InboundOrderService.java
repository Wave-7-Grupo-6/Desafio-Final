package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
          inboundOrder.setBatchs(inboundOrder.getBatchs().stream().map(batchDTO -> batch.save(batchDTO)).collect(Collectors.toList()));
          return inboundOrder.getBatchs().stream().map(batch -> new BatchStockDTO(batch)).collect(Collectors.toList());
     }

     @Override
     public InboundOrder findById(Long id) {
          return repository.findById(id).orElse(null);
     }

     private InboundOrder createAtributes(InboundOrderDTO inboundOrderDTO){
          List<Announcement> annoucements = inboundOrderDTO.getBatchStockDTOList().stream()
                  .map(batch -> announcement.findById(batch.getProductId())).collect(Collectors.toList());
          InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, section.findById(inboundOrderDTO.getSectionId()), annoucements);
          return inboundOrder;
     }

     @Override
     public List<BatchStockDTO> update(Long id, InboundOrderDTO inboundOrderDTO){
          if(repository.existsById(id)){
               System.out.println("------ TESTE ----- ");
               //delete(id);
               System.out.println("---- teste ---- ");
               return save(inboundOrderDTO);
          }
          throw new NotFoundException("Inbound Order not found.");
     }

     public void delete(Long id){
          if(repository.existsById(id)){
               repository.deleteById(id);
          }
          throw new NotFoundException("Inbound Order not found.");
     }
}
