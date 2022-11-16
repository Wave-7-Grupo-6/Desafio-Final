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
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated warehouse ID")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The warehouse name")
    private String name;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The warehouse sections")
    private List<Section> sections;

    public Warehouse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}