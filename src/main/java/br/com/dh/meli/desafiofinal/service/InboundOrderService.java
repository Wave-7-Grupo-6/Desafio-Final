package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.exceptions.NoCompatibleException;
import br.com.dh.meli.desafiofinal.exceptions.NoSpaceAvailableException;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class InboundOrderService implements IInboundOrder{
     private final InboundOrderRepository repository;
     private final ISection section;
     private final IAnnouncement announcement;
     private final IWarehouse warehouse;

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
          Float volumeTotal = 0f;

          for(int i = 0; i < inboundOrderDTO.getBatchStockDTOList().size(); i++){
               BatchStockDTO batchStockDTO = inboundOrderDTO.getBatchStockDTOList().get(i);
               inboundOrder.getBatchs().add(batchStockDTO.createBatch(batchStockDTO, inboundOrder, announcement.findById(batchStockDTO.getProductId())));

               volumeTotal += batchStockDTO.getVolume();
          }

          validations(inboundOrder, volumeTotal, inboundOrderDTO.getWarehouseId());

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

     private void validations(InboundOrder inboundOrder, Float volumeTotal, Long warehouseId){
          validateSpaceAvaliable(inboundOrder, volumeTotal);
          validateCategory(inboundOrder);
          validateSellerWarehouse(inboundOrder, warehouseId);
     }

     private void validateSpaceAvaliable(InboundOrder inboundOrder, Float volumeTotal){
          inboundOrder.getSection().setVolumeOccupied(0f);

          if(inboundOrder.getSection().getVolumeOccupied() + volumeTotal > inboundOrder.getSection().getVolumeMax())
               throw new NoSpaceAvailableException("No space avaliable in this section");
     }

     private void validateCategory(InboundOrder inboundOrder){
          for (Batch batch : inboundOrder.getBatchs()){
               if(!Objects.equals(batch.getAnnouncement().getCategory().getId(), inboundOrder.getSection().getId()))
                    throw new NoCompatibleException("Section no compatible of product's category");
          }
     }

     private void validateSellerWarehouse(InboundOrder inboundOrder, Long warehouseId){
          for (Batch batch : inboundOrder.getBatchs()){
               for(Section section : batch.getAnnouncement().getSeller().getSections()){
                    if(!Objects.equals(section.getWarehouse().getId(), warehouse.findById(warehouseId).getId()))
                         throw new NoCompatibleException("Section not compatible of warehouse");
               }
          }
     }
}
