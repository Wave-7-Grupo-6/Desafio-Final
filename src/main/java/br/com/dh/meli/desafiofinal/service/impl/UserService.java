package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.model.User;
import br.com.dh.meli.desafiofinal.repository.UserRepository;
import br.com.dh.meli.desafiofinal.service.IUser;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService, IUser {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            log.error("User not found ["+username+"].");
            throw new UsernameNotFoundException("User ["+username+"] not found.");
        } else {
            log.info("Loading user ["+username+"].");
            return user.get();
        }

    }

    @Override
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}
