package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float volumeMax;

    @Column(nullable = false)
    private Float temperature;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("sections")
    @JsonBackReference
    private Category type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("warehouse")
    @JsonBackReference
    private Warehouse warehouse;
}