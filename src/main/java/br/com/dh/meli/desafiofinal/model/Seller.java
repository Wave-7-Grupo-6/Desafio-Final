package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("seller")
    private Set<Announcement> announcements;

}
