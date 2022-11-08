package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @Column(nullable = false)
    private String warehouseName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnoreProperties("warehouse")
    @JsonBackReference
    private Provider provider;
}
