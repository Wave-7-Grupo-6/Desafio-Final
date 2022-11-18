package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * The type Client dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Size(max = 50, message = "Name size must not exceed 50 characters.")
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    /**
     * Instantiates a new Client dto.
     *
     * @param client the client
     */
    public ClientDTO(Client client) {
        this.name = client.getName();
    }
}
