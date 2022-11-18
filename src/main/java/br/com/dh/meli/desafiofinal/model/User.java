package br.com.dh.meli.desafiofinal.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

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

    @OneToMany
    @ApiModelProperty(notes = "The user roles")
    private List<Role> roles;

    @ApiModelProperty(notes = "The user account expiration status")
    private Boolean isAccountNonExpired;

    @ApiModelProperty(notes = "The user account locked status")
    private Boolean isAccountNonLocked;

    @ApiModelProperty(notes = "The user credentials expiration status")
    private Boolean isCredentialsNonExpired;

    @ApiModelProperty(notes = "The user enabled status")
    private Boolean isEnabled;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
