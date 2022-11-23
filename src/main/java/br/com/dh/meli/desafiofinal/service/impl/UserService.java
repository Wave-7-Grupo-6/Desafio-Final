package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotUniqueException;
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

/**
 * The type User service.
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService, IUser {

    private final UserRepository userRepository;
    private final IRole roleService;
    private final PasswordEncoder encoder;

    /**
     * If the user is found, return a UserPrincipal object, otherwise throw a UsernameNotFoundException
     *
     * @param username The username of the user to load.
     * @return UserPrincipal
     */
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

    /**
     * > This function checks if a user exists in the database by their username
     *
     * @param username The username of the user to check for.
     * @return A boolean value.
     */
    @Override
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    /**
     * > It saves a user to the database
     *
     * @param userDTO The user object that we want to save.
     * @return UserDTO
     */
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
