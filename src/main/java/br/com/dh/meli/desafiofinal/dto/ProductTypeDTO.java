package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDTO {
    private Long productTypeId;
    private List<WarehouseStockDTO> warehouses;

    public ProductTypeDTO(ProductType productType, List<WarehouseStockDTO> warehouses) {
        this.productTypeId = getProductTypeId();
        this.warehouses = warehouses;
    }
}
