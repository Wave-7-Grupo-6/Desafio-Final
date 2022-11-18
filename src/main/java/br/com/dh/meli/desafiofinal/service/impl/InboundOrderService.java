package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.BatchStockDTO;
import br.com.dh.meli.desafiofinal.dto.InboundOrderDTO;
import br.com.dh.meli.desafiofinal.exceptions.NoCompatibleException;
import br.com.dh.meli.desafiofinal.exceptions.NoSpaceAvailableException;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.InboundOrderRepository;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.IInboundOrder;
import br.com.dh.meli.desafiofinal.service.ISection;
import br.com.dh.meli.desafiofinal.service.IWarehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Inbound order service.
 */
@Service
@RequiredArgsConstructor
public class InboundOrderService implements IInboundOrder {
     private final InboundOrderRepository repository;
     private final ISection section;
     private final IAnnouncement announcement;
     private final IWarehouse warehouse;

     /**
      * It saves the inbound order and returns a list of batch stock DTOs.
      *
      * @param inboundOrderDTO The object that contains the data that will be saved in the database.
      * @return A list of BatchStockDTO
      */
     @Override
     public List<BatchStockDTO> save(InboundOrderDTO inboundOrderDTO){
         InboundOrder inboundOrder = repository.save(createAtributes(inboundOrderDTO));

         return inboundOrder.getBatchs().stream().map(BatchStockDTO::new).collect(Collectors.toList());
     }

     /**
      * If the order exists, return it, otherwise return null.
      *
      * @param id The id of the InboundOrder to be found.
      * @return The repository.findById(id) is being returned.
      */
     @Override
     public InboundOrder findById(Long id) {
          return repository.findById(id).orElse(null);
     }

     /**
      * It creates an InboundOrder object with the attributes of the InboundOrderDTO object, and then creates a list of
      * BatchStock objects with the attributes of the BatchStockDTO objects
      *
      * @param inboundOrderDTO The object that contains the data that will be used to create the inbound order.
      * @return The method is returning an object of type InboundOrder.
      */
     private InboundOrder createAtributes(InboundOrderDTO inboundOrderDTO){
          InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, section.findById(inboundOrderDTO.getSectionId()));
          Float volumeTotal = 0f;

          for(int i = 0; i < inboundOrderDTO.getBatchStockList().size(); i++){
               BatchStockDTO batchStockDTO = inboundOrderDTO.getBatchStockList().get(i);
               inboundOrder.getBatchs().add(batchStockDTO.createBatch(batchStockDTO, inboundOrder, announcement.findById(batchStockDTO.getProductId())));

               volumeTotal += batchStockDTO.getVolume();
          }

          validations(inboundOrder, volumeTotal, inboundOrderDTO.getWarehouseId());

          return inboundOrder;
     }

     /**
      * If the inbound order exists, update it and return the list of batch stock DTOs
      *
      * @param id The id of the inbound order to be updated.
      * @param inboundOrderDTO The DTO object that contains the data to be updated.
      * @return A list of BatchStockDTO objects.
      */
     @Override
     public List<BatchStockDTO> update(Long id, InboundOrderDTO inboundOrderDTO){
          if(repository.existsById(id)){
               inboundOrderDTO.setId(id);
               return save(inboundOrderDTO);
          }
          throw new NotFoundException("Inbound Order not found.");
     }

     /**
      * ValidateSpaceAvaliable, validateCategory and validateSellerWarehouse are called in sequence, and the result of each
      * is ignored.
      *
      * @param inboundOrder The inbound order to be validated.
      * @param volumeTotal The total volume of the products in the order.
      * @param warehouseId The warehouse where the order will be stored.
      */
     private void validations(InboundOrder inboundOrder, Float volumeTotal, Long warehouseId){
          validateSpaceAvaliable(inboundOrder, volumeTotal);
          validateCategory(inboundOrder);
          validateSellerWarehouse(inboundOrder, warehouseId);
     }

     /**
      * If the volume occupied plus the volume total is greater than the volume max, throw an exception.
      *
      * @param inboundOrder The inbound order that is being validated
      * @param volumeTotal The total volume of all the items in the order
      */
     private void validateSpaceAvaliable(InboundOrder inboundOrder, Float volumeTotal){
          inboundOrder.getSection().setVolumeOccupied(0f);

          if(inboundOrder.getSection().getVolumeOccupied() + volumeTotal > inboundOrder.getSection().getVolumeMax())
               throw new NoSpaceAvailableException("No space avaliable in this section");
     }

     /**
      * > If the category of the product is not the same as the section, then throw an exception
      *
      * @param inboundOrder The object that will be validated.
      */
     private void validateCategory(InboundOrder inboundOrder){
          for (Batch batch : inboundOrder.getBatchs()){
               if(!Objects.equals(batch.getAnnouncement().getCategory().getId(), inboundOrder.getSection().getId()))
                    throw new NoCompatibleException("Section no compatible of product's category");
          }
     }

     /**
      * > This function validates if the warehouse of the seller is the same as the warehouse of the section
      *
      * @param inboundOrder The object that will be validated.
      * @param warehouseId The warehouse id that the seller wants to send the product to.
      */
     private void validateSellerWarehouse(InboundOrder inboundOrder, Long warehouseId){
          for (Batch batch : inboundOrder.getBatchs()){
               for(Section section : batch.getAnnouncement().getSeller().getSections()){
                    if(!Objects.equals(section.getWarehouse().getId(), warehouse.findById(warehouseId).getId()))
                         throw new NoCompatibleException("Section not compatible of warehouse");
               }
          }
     }
}
