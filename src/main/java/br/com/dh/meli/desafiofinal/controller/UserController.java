package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import br.com.dh.meli.desafiofinal.exceptions.InvalidTokenException;
import br.com.dh.meli.desafiofinal.security.JwtTokenUtil;
import br.com.dh.meli.desafiofinal.security.UserPrincipal;
import br.com.dh.meli.desafiofinal.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @ApiOperation(value = "Login user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User logged successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody @Valid UserDTO userDTO) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getUsername(),  userDTO.getPassword())
            );
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(request,userPrincipal);
            String refreshToken = jwtTokenUtil.generateRefreshToken(request, userPrincipal);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);

            return ResponseEntity.ok().body(tokens);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/refresh/token")
    @ApiOperation(value = "Renew access token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Access token renewed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public ResponseEntity<?> renewAccessToken(HttpServletRequest request) throws IOException {
        String ATTRIBUTE_PREFIX = "Bearer ";
        String attribute = request.getHeader(AUTHORIZATION);
        if(attribute==null || !attribute.startsWith(ATTRIBUTE_PREFIX)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            String refreshToken = attribute.replace(ATTRIBUTE_PREFIX, "");

            if(!jwtTokenUtil.validateJwtToken(refreshToken)) {
                throw new InvalidTokenException("Invalid token.");
            }
            String username = jwtTokenUtil.getSubject(refreshToken);
            UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(username);

            String accessToken = jwtTokenUtil.generateAccessToken(request,user);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);

            return ResponseEntity.ok().body(tokens);

        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new admin user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Admin user created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public void save(@RequestBody @Valid UserDTO userDTO){
        userService.save(userDTO);
    }

}
