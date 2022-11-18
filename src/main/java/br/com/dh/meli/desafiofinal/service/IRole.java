package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Role;

import java.util.List;

public interface IRole {
    Role save(Role role);
    Role update(Role role);
    void deleteById(Long id);
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
}
