package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Warehouse;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * The type Warehouse dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    @Size(max = 50, message = "Name size must not exceed 50 characters.")
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    /**
     * Instantiates a new Warehouse dto.
     *
     * @param warehouse the warehouse
     */
    public WarehouseDTO(Warehouse warehouse) {
        this.name = warehouse.getName();
    }
}
