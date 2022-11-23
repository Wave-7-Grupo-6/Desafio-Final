package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.NotUniqueException;
import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.repository.RoleRepository;
import br.com.dh.meli.desafiofinal.service.IRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getClientRole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    private IRole roleService;

    @BeforeEach
    void setUp() {
        roleService = new RoleService(roleRepository);
    }

    @Test
    void save_returnRole_whenSuccess() {
        Role role = getClientRole();
        when(roleRepository.save(any())).thenReturn(role);
        when(roleRepository.findByName(any())).thenReturn(Optional.empty());
        
        Role savedRole = roleService.save(role);

        assertThat(savedRole.getId()).isEqualTo(role.getId());
        assertThat(savedRole.getName()).isEqualTo(role.getName());
    }

    @Test
    void save_throwsNotUniqueException_whenRoleAlreadyExists() {
        Role role = getClientRole();
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));

        assertThrows(NotUniqueException.class, () -> roleService.save(role));
    }

    @Test
    void update_returnsUpdatedRole_whenSuccess() {
        Role role = getClientRole();
        when(roleRepository.save(any())).thenReturn(role);
        when(roleRepository.findByName(any())).thenReturn(Optional.empty());
        when(roleRepository.existsById(any())).thenReturn(true);

        Role savedRole = roleService.update(role);

        assertThat(savedRole.getId()).isEqualTo(role.getId());
        assertThat(savedRole.getName()).isEqualTo(role.getName());
    }

    @Test
    void update_throwsNotFoundException_whenRoleDoesntExists() {
        Role role = getClientRole();
        when(roleRepository.existsById(any())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> roleService.update(role));
    }

    @Test
    void update_throwsNotUniqueException_whenRoleAlreadyExists() {
        Role role = getClientRole();
        when(roleRepository.existsById(any())).thenReturn(true);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));

        assertThrows(NotUniqueException.class, () -> roleService.update(role));
    }

    @Test
    void deleteById_throwsNotFoundException_whenRoleDoesntExists() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.deleteById(1L));
    }

    @Test
    void deleteById_returnVoid_whenSuccess() {
        Role role = getClientRole();
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));

        roleService.deleteById(1L);

        verify(roleRepository, times(1)).deleteById(1L);
    }

    @Test
    void findById_throwsNotFoundException_whenRoleDoesntExists() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.findById(1L));
    }

    @Test
    void findById_returnRole_whenSuccess() {
        Role role = getClientRole();
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));

        Role foundRole = roleService.findById(1L);

        assertThat(foundRole.getId()).isEqualTo(role.getId());
        assertThat(foundRole.getName()).isEqualTo(role.getName());
    }

    @Test
    void findByName_returnRole_whenSuccess() {
        Role role = getClientRole();
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));

        Role foundRole = roleService.findByName(role.getName());

        assertThat(foundRole.getId()).isEqualTo(role.getId());
        assertThat(foundRole.getName()).isEqualTo(role.getName());
    }

    @Test
    void findByName_throwsNotFoundException_whenRoleDoesntExists() {
        Role role = getClientRole();
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.findByName(role.getName()));
    }

    @Test
    void findAll_throwsNotFoundException_whenRoleDoesntExists() {
        when(roleRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> roleService.findAll());
    }

    @Test
    void findAll_returnRolesList_whenSuccess() {
        Role role = getClientRole();
        when(roleRepository.findAll()).thenReturn(List.of(role));

        List<Role> roles = roleService.findAll();

        assertThat(roles.size()).isEqualTo(1);
        assertThat(roles.get(0).getId()).isEqualTo(role.getId());
        assertThat(roles.get(0).getName()).isEqualTo(role.getName());
    }
}