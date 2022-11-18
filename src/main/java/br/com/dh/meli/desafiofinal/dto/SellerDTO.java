package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * The type Seller dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {

    @Size(max = 50, message = "Name size must not exceed 50 characters.")
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    /**
     * Instantiates a new Seller dto.
     *
     * @param seller the seller
     */
    public SellerDTO(Seller seller) {
        this.name = seller.getName();
    }
}
