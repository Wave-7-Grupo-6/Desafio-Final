package br.com.dh.meli.desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * The type Client.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated client ID")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The client name")
    private String name;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    @JsonManagedReference
    @ToString.Exclude
    @ApiModelProperty(notes = "The client carts")
    private List<Cart> carts;

    @ManyToMany
    @JoinTable(
            name = "favorite_list",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "announcement_id")
    )
    @JsonIgnoreProperties({"favoritedBy","batchs","cartItems","seller"})
    private List<Announcement> favorites;
}
