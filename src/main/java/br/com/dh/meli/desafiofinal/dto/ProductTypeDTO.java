package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The type Product type dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDTO {
    private Long productTypeId;
    private List<WarehouseStockDTO> warehouses;

    /**
     * Instantiates a new Product type dto.
     *
     * @param productType the product type
     * @param warehouses  the warehouses
     */
    public ProductTypeDTO(ProductType productType, List<WarehouseStockDTO> warehouses) {
        this.productTypeId = getProductTypeId();
        this.warehouses = warehouses;
    }
}
