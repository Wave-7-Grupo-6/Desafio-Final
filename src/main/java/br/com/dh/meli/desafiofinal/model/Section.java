package br.com.dh.meli.desafiofinal.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    // @Column(nullable = false)
    // @JsonIgnoreProperties(...)
    // private Category type;
}


