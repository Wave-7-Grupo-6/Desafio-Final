package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.UserDTO;

public interface IUser {
    Boolean existsByUsername(String username);
    UserDTO save(UserDTO userDTO);
}
