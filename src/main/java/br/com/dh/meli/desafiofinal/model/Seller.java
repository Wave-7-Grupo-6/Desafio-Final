package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The type Seller.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Seller extends User{

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

    /**
     * Instantiates a new Seller.
     *
     * @param id   the id
     * @param name the name
     */
    public Seller(Long id, String username, String password, Set<Role> roles, String name) {
        super(id, username, password, roles, true, true, true, true);
        this.name = name;
    }

    public Seller(SellerDTO sellerDTO, Set<Role> roles) {
        super(null, sellerDTO.getUsername(), sellerDTO.getPassword(), roles, true, true, true, true);
        this.name = sellerDTO.getName();
    }
}
