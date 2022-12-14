package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.ProductTypeDTO;
import br.com.dh.meli.desafiofinal.dto.WarehouseStockDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.repository.AnnouncementRepository;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.ICategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Announcement service.
 */
@Service
@RequiredArgsConstructor
public class AnnouncementService implements IAnnouncement {
    private final AnnouncementRepository repository;
    private final ICategory catService;

    /**
     * This method is responsible for finding an announcement by id.
     * @param id
     * @return the announcement
     */
    @Override
    public Announcement findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Announcement not found."));
    }

    /**
     * This method is responsible for saving an announcement.
     * @param announcement
     * @return the saved announcement
     */
    @Override
    public Announcement save(Announcement announcement) {
        return repository.save(announcement);
    }

    /**
     * This method is responsible for returning all announcements.
     */
    @Override
    public List<Announcement> findAll() {
        return repository.findAll();
    }

    /**
     * This method is responsible for finding all announcements by category.
     * @param category
     * @return the list
     */
    @Override
    public List<Announcement> findByCategory(String category) {
        Long id_cat = catService.findByName(category).getId();

        List<Announcement> list = repository.findByCategory_Id(id_cat);

        if(list.isEmpty()) throw new NotFoundException("Announcement list of category not found");

        return list;
    }

    /**
     * This method is responsible for finding all announcements by product type.
     * @param prod_id product type id
     * @return the list
     */
    @Override
    public List<Announcement> findByProductType(Long prod_id) {
        return repository.findByProductType_Id(prod_id);
    }

    /**
     * This method is responsible for finding all announcements by product type and grouping by warehouse.
     * @param prod_id product type id
     * @return the product type DTO
     */
    @Override
    public ProductTypeDTO findByProductTypeGroupByWarehouse(Long prod_id){
        List<Announcement> announcements = findByProductType(prod_id);
        List<WarehouseStockDTO> warehouseStockDTOs = new ArrayList<>();

        for(Announcement announcement : announcements){
            int quantity = 0;

            for(Batch batch : announcement.getBatchs()) quantity += batch.getProductQuantity();

            for(Section section : announcement.getSeller().getSections()){
                if(warehouseStockDTOs.stream().anyMatch(warehouseStockDTO ->
                        Objects.equals(warehouseStockDTO.getWarehouseCode(), section.getWarehouse().getId())))
                    continue;
                warehouseStockDTOs.add(new WarehouseStockDTO(section.getWarehouse(), quantity));
            }
        }

        return new ProductTypeDTO(prod_id, warehouseStockDTOs);
    }
}
