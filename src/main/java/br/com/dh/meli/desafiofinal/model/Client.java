package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
}
