package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {
    private String name;

    public SellerDTO(Seller seller) {
        this.name = seller.getName();
    }
}
