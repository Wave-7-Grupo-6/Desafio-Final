package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.UserDTO;
import br.com.dh.meli.desafiofinal.model.User;

public interface IUser {
    Boolean existsByUsername(String username);
    UserDTO save(UserDTO userDTO);
}
