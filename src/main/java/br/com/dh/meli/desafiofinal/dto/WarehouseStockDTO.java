package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStockDTO {
    private Long warehouseCode;
    private int quantity;

    public WarehouseStockDTO(Warehouse warehouse, int quantity) {
        this.warehouseCode = warehouse.getId();
        this.quantity = quantity;
    }
}
