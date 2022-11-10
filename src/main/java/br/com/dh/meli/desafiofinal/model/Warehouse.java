package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    @JsonManagedReference
    @ToString.Exclude
    private List<Section> sections;

    public Warehouse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}