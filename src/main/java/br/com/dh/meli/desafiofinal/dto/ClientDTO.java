package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Size(max = 50, message = "Name size must not exceed 50 characters.")
    @NotEmpty(message = "Name must not be empty.")
    private String name;

    @Size(max = 15, message = "Username must not exceed 50 characters.")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    /*
        ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once
        (?=\S+$)          # no whitespace allowed in the entire string
        .{8,}             # anything, at least eight places though
        $                 # end-of-string
    * */
    private String password;

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.username = client.getUsername();
        this.password = client.getPassword();
    }
}
