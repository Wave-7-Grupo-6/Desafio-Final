package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Warehouse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    private String name;

    public WarehouseDTO(Warehouse warehouse) {
        this.name = warehouse.getName();
    }
}
