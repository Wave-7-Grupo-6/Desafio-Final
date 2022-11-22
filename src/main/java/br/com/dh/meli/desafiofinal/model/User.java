package br.com.dh.meli.desafiofinal.model;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated user ID")
    private Long id;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(notes = "The unique username")
    private String username;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The user password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @ApiModelProperty(notes = "The user roles")
    private Set<Role> roles;

    @ApiModelProperty(notes = "The user account expiration status")
    private Boolean accountNonExpired;

    @ApiModelProperty(notes = "The user account locked status")
    private Boolean accountNonLocked;

    @ApiModelProperty(notes = "The user credentials expiration status")
    private Boolean credentialsNonExpired;

    @ApiModelProperty(notes = "The user enabled status")
    private Boolean enabled;

    public User(UserDTO userDTO, Set<Role> roles) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.roles = roles;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
