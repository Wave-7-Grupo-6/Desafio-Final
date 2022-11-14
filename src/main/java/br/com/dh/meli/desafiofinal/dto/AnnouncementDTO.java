package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Announcement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {

    private Long id;

    @Size(max = 50, message = "Description size must not exceed 50 characters.")
    @NotEmpty(message = "Description must not be empty.")
    private String description;

    @Positive(message = "Category id must be positive.")
    @NotNull(message = "Category id must not be null.")
    private Long categoryId;

    @Positive(message = "Seller id must be positive.")
    @NotNull(message = "Seller id must not be null.")
    private Long sellerId;

    @Positive(message = "ProductType id must be positive.")
    @NotNull(message = "ProductType id must not be null.")
    private Long productTypeId;

    public AnnouncementDTO(Announcement announcement) {
        this.id = announcement.getId();
        this.description = announcement.getDescription();
        this.categoryId = announcement.getCategory().getId();
        this.sellerId = announcement.getSeller().getId();
        this.productTypeId = announcement.getProductType().getId();
    }
}
