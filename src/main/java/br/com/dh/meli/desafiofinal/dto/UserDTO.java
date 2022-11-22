package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * The type User dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Email(message = "Username must be a valid email.")
    @NotEmpty(message = "Username must not be empty.")
    private String username;
    private String password;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
