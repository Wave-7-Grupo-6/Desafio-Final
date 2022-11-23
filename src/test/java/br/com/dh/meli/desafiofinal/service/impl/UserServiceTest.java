package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotUniqueException;
import br.com.dh.meli.desafiofinal.model.User;
import br.com.dh.meli.desafiofinal.repository.UserRepository;
import br.com.dh.meli.desafiofinal.service.IRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getAdminRole;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private IRole roleService;

    @Mock
    private PasswordEncoder encoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository,roleService,encoder);
    }

    @Test
    void loadUserByUsername_returnsUserDetails_whenUsernameExists() {
        User user = getUser();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDetails.isEnabled()).isEqualTo(user.getEnabled());
        assertThat(userDetails.isAccountNonExpired()).isEqualTo(user.getAccountNonExpired());
        assertThat(userDetails.isAccountNonLocked()).isEqualTo(user.getAccountNonLocked());
        assertThat(userDetails.isCredentialsNonExpired()).isEqualTo(user.getAccountNonExpired());
    }

    @Test
    void loadUserByUsername_throwsUsernameNotFoundException_whenUsernameAlreadyExists() {
        User user = getUser();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(user.getUsername()));
    }

    @Test
    void existsByUsername_returnsTrue_whenUsernameExists() {
        when(userService.existsByUsername(anyString())).thenReturn(true);

        assertThat(userService.existsByUsername("USERNAME")).isTrue();
    }

    @Test
    void existsByUsername_returnsFalse_whenUsernameDoesntExist() {
        when(userService.existsByUsername(anyString())).thenReturn(false);

        assertThat(userService.existsByUsername("USERNAME")).isFalse();
    }

    @Test
    void save_returnUser_whenSuccess() {
        User user = getUser();
        when(roleService.findByName("ROLE_ADMIN")).thenReturn(getAdminRole());
        when(userService.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO savedUser = userService.save(new UserDTO(user));

        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void save_throwsNotUniqueException_whenUsernameAlreadyExists() {
        User user = getUser();
        when(roleService.findByName("ROLE_ADMIN")).thenReturn(getAdminRole());
        when(userService.existsByUsername(anyString())).thenReturn(true);

        assertThrows(NotUniqueException.class, () -> userService.save(new UserDTO(user)));
    }
}