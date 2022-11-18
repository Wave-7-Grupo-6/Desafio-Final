package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Warehouse stock dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStockDTO {
    private Long warehouseCode;
    private int quantity;

    /**
     * Instantiates a new Warehouse stock dto.
     *
     * @param warehouse the warehouse
     * @param quantity  the quantity
     */
    public WarehouseStockDTO(Warehouse warehouse, int quantity) {
        this.warehouseCode = warehouse.getId();
        this.quantity = quantity;
    }
}
