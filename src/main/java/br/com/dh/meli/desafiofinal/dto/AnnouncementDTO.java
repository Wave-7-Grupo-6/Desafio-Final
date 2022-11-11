package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Announcement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {

    private Long id;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private Long sellerId;
    private Long productTypeId;

    public AnnouncementDTO(Announcement announcement) {
        this.id = announcement.getId();
        this.description = announcement.getDescription();
        this.price = announcement.getPrice();
        this.categoryId = announcement.getCategory().getId();
        this.sellerId = announcement.getSeller().getId();
        this.productTypeId = announcement.getProductType().getId();
    }
}
