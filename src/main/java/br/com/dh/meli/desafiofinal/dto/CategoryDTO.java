package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private Float temperature;

    public CategoryDTO(Category category){
        this.name = category.getName();
        this.temperature = category.getTemperature();
    }
}
