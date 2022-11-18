package br.com.dh.meli.desafiofinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user!=null){
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getEnabled();
    }
}
