package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

     private InboundOrder createAtributes(InboundOrderDTO inboundOrderDTO){

          System.out.println(inboundOrderDTO.getBatchStockDTOList());
          List<Announcement> annoucements = inboundOrderDTO.getBatchStockDTOList().stream()
                  .map(batch -> announcement.findById(batch.getProductId())).collect(Collectors.toList());
          InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, section.findById(inboundOrderDTO.getSectionId()), annoucements);
          return inboundOrder;
     }

}
