package br.com.dh.meli.desafiofinal.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Charities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "The name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "The CNPJ is required")
    @CNPJ(message = "CNPJ should be valid")
    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "The email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;
}
