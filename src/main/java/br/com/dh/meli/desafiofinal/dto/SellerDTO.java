package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Seller;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

    @Email(message = "Username must be a valid email.")
    @NotEmpty(message = "Username must not be empty.")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least 1 digit, 1 uppercase letter, 1 lowercase letter, 1 special character and be at least 8 characters long with no spaces.")
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

    /**
     * Instantiates a new Seller dto.
     *
     * @param seller the seller
     */
    public SellerDTO(Seller seller) {
        this.name = seller.getName();
        this.username = seller.getUsername();
        this.password = seller.getPassword();
    }
}
