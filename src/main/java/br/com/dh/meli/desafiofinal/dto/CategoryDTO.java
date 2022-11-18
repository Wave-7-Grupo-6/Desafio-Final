package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Category dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @Size(max = 30, message = "Name size must not exceed 30 characters.")
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    @NotNull(message = "Temperature must not be null.")
    private Float temperature;

    /**
     * Instantiates a new Category dto.
     *
     * @param category the category
     */
    public CategoryDTO(Category category){
        this.name = category.getName();
        this.temperature = category.getTemperature();
    }
}
