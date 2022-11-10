package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;

    public ClientDTO(Client client) {
        this.name = client.getName();
    }
}
