package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * The type Product dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Positive(message = "Product id must be positive.")
    @NotNull(message = "Product id must not be null.")
    private Long productId;

    @Positive(message = "Quantity must be positive.")
    @NotNull(message = "Quantity must not be null.")
    private int quantity;

    @Positive(message = "Batch id must be positive.")
    private Long batchId;
}
