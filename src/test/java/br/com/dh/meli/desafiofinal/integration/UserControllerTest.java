package br.com.dh.meli.desafiofinal.integration;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import br.com.dh.meli.desafiofinal.security.JwtTokenUtil;
import br.com.dh.meli.desafiofinal.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void login_returnTokensAndOKStatus_whenUserValid() throws Exception {
        mockMvc.perform(
                        post("/api/v1/user/login")
                                .content(mapper.writeValueAsString(new UserDTO(getUser())))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", CoreMatchers.any(String.class)))
                .andExpect(jsonPath("$.refresh_token", CoreMatchers.any(String.class)));
    }

    @Test
    void renewAccessToken() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/api/v1/user/login")
                        .content(mapper.writeValueAsString(new UserDTO(getUser())))
                        .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String refreshToken = JsonPath.read(result.getResponse().getContentAsString(), "$.refresh_token");
        String authorization = "Bearer "+refreshToken;

        mockMvc.perform(
                        get("/api/v1/user/refresh/token")
                                .header(HttpHeaders.AUTHORIZATION, authorization)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", CoreMatchers.any(String.class)))
                .andExpect(jsonPath("$.refresh_token", CoreMatchers.any(String.class)));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void save_returnUserDTOAndCreatedStatus_whenUserValid() throws Exception {
        UserDTO userDTO = new UserDTO("newUser@user.com", "aaaaaA1@");

        mockMvc.perform(
                        post("/api/v1/user")
                                .content(mapper.writeValueAsString(userDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", CoreMatchers.is(userDTO.getUsername())));
    }
}