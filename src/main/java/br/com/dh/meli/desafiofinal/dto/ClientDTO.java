package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Size(max = 50, message = "Name size must not exceed 50 characters.")
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    public ClientDTO(Client client) {
        this.name = client.getName();
    }
}
