package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.security.JwtTokenFilter;
import br.com.dh.meli.desafiofinal.service.IRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getAdminRole;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
class RoleControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IRole roleService;

    @MockBean
    private JwtTokenFilter filter;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void findAll_returnRolesListAndOKStatus_whenRoleValid() throws Exception {
        Role role = getAdminRole();
        when(roleService.findAll()).thenReturn(List.of(role));
        mockMvc.perform(
                        get("/api/v1/role")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(getAdminRole().getName())));
        verify(this.roleService, times(1)).findAll();
    }

    @Test
    void findById_returnRoleAndOKStatus_whenRoleValid() throws Exception {
        Role role = getAdminRole();
        when(roleService.findById(anyLong())).thenReturn(role);
        mockMvc.perform(
                        get("/api/v1/role/{id}", role.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(getAdminRole().getName())));
        verify(this.roleService, times(1)).findById(anyLong());
    }

    @Test
    void save_returnRoleAndCreatedStatus_whenRoleValid() throws Exception {
        Role role = getAdminRole();
        when(roleService.save(any(Role.class))).thenReturn(role);
        mockMvc.perform(
                        post("/api/v1/role")
                                .content(mapper.writeValueAsString(role))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(getAdminRole().getName())));
        verify(this.roleService, times(1)).save(any(Role.class));
    }

    @Test
    void update_returnRoleAndCreatedStatus_whenRoleValid() throws Exception {
        Role role = getAdminRole();
        when(roleService.update(any(Role.class))).thenReturn(role);
        mockMvc.perform(
                        put("/api/v1/role")
                                .content(mapper.writeValueAsString(role))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(getAdminRole().getName())));
        verify(this.roleService, times(1)).update(any(Role.class));
    }

    @Test
    void deleteById_returnsNoContentStatus_whenRoleValid() throws Exception {
        Role role = getAdminRole();
        mockMvc.perform(
                        delete("/api/v1/role/{id}", role.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(this.roleService, times(1)).deleteById(anyLong());
    }
}