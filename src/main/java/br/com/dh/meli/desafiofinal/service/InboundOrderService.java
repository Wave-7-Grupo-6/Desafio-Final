package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
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

     @Override
     public List<Batch> save(InboundOrderDTO inboundOrderDTO){
          repository.save(createAtributes(inboundOrderDTO));
          return createAtributes(inboundOrderDTO).getBatchs();
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
     public List<Batch> update(Long id, InboundOrderDTO inboundOrderDTO){
          System.out.println(id);
          System.out.println("-------> TESTE <------");
          System.out.println(inboundOrderDTO);
          if(repository.existsById(id)){
               repository.save(createAtributes(inboundOrderDTO));
               return createAtributes(inboundOrderDTO).getBatchs();
          }
          return null;
     }


}
