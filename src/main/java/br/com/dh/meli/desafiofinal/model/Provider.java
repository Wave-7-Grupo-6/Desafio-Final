package br.com.dh.meli.desafiofinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    @Column(nullable = false)
    private String providerName;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.REFRESH)
    private List<Warehouse> warehouse;
}
