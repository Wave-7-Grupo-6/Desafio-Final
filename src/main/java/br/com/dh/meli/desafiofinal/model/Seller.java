package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    @ApiModelProperty(notes = "The database generated seller ID")
    private Long id;

    @Column (nullable = false)
    @ApiModelProperty(notes = "The seller name")
    private String name;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("seller")
    @ApiModelProperty(notes = "The seller announcements")
    private Set<Announcement> announcements;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The seller sections")
    private List<Section> sections;

    public Seller(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
