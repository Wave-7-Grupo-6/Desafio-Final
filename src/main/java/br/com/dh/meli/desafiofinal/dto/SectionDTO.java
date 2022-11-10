package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {
    private Long id;
    private Long warehouseId;

    public SectionDTO(Section section) {
        this.id = section.getId();
        this.warehouseId = section.getWarehouse().getId();
    }
}
