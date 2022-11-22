package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotUniqueException;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.model.User;
import br.com.dh.meli.desafiofinal.repository.UserRepository;
import br.com.dh.meli.desafiofinal.security.UserPrincipal;
import br.com.dh.meli.desafiofinal.service.IRole;
import br.com.dh.meli.desafiofinal.service.IUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService, IUser {

    private final UserRepository userRepository;
    private final IRole roleService;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.error("User not found [" + username + "].");
            throw new UsernameNotFoundException("User [" + username + "] not found.");
        } else {
            log.info("Loading user [" + username + "].");
            return new UserPrincipal(user.get());
        }

    }

    @Override
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        Role role = roleService.findByName("ROLE_ADMIN");
        Boolean existsByUsername = userRepository.existsByUsername(userDTO.getUsername());
        if(existsByUsername){
            throw new NotUniqueException("Email already taken.");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO, Set.of(role));
        return new UserDTO(userRepository.save(user));
    }
}
