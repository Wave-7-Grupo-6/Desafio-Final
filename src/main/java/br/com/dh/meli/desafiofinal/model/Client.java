package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Client extends User{

    @Column(nullable = false)
    @ApiModelProperty(notes = "The client name")
    private String name;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The client carts")
    private List<Cart> carts;

    public Client(Long id, String username, String password, Set<Role> roles, String name) {
        super(id, username, password, roles, true, true, true, true);
        this.name = name;
    }

    public Client(ClientDTO clientDTO, Set<Role> roles) {
        super(null, clientDTO.getUsername(), clientDTO.getPassword(), roles, true, true, true, true);
        this.name = name;
    }
}
