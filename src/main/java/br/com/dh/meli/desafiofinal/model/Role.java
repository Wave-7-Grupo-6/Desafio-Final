package br.com.dh.meli.desafiofinal.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated role ID")
    private Long id;

    @ApiModelProperty(notes = "The role name")
    @Column(nullable = false, unique = true)
    private String name;

}
