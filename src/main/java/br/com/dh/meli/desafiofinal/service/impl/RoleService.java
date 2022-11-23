package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.NotUniqueException;
import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.repository.RoleRepository;
import br.com.dh.meli.desafiofinal.service.IRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Role service.
 */
@Service
@RequiredArgsConstructor
public class RoleService implements IRole {

    private final RoleRepository roleRepository;

    /**
     * If the role name is unique, save the role.
     *
     * @param role The role object that is being saved.
     * @return A Role object
     */
    public Role save(Role role) {
        checkUniqueRole(role.getName());
        role.setId(null);
        return roleRepository.save(role);
    }

    /**
     * If the role id is null or the role doesn't exist, throw an exception. Otherwise, check if the role name is unique
     * and save the role
     *
     * @param role The role object that is being updated.
     * @return The role object is being returned.
     */
    @Override
    public Role update(Role role) {
        if(role.getId() == null || !roleRepository.existsById(role.getId())){
            throw new NotFoundException("Role id not provided or not found.");
        }
        checkUniqueRole(role.getName());
        return roleRepository.save(role);
    }

    /**
     * If the role exists, delete it.
     *
     * @param id The id of the role to be deleted.
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    /**
     * If the role exists, return it, otherwise throw an exception.
     *
     * @param id The id of the role to be found.
     * @return A Role object.
     */
    @Override
    public Role findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElseThrow(() -> new NotFoundException("Role not found."));
    }

    /**
     * Find a role by name, or throw a NotFoundException if it doesn't exist.
     *
     * @param name The name of the role.
     * @return A Role object.
     */
    @Override
    public Role findByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        return role.orElseThrow(() -> new NotFoundException("Role not found."));
    }

    /**
     * > Find all roles in the database and return them in a list. If no roles are found, throw a NotFoundException
     *
     * @return A list of all the roles in the database.
     */
    @Override
    public List<Role> findAll() {
        List<Role> roleList = roleRepository.findAll();
        if(roleList.isEmpty()){
            throw new NotFoundException("No Role found");
        }
        return roleList;
    }

    /**
     * If a role with the same name already exists, throw a NotUniqueException
     *
     * @param roleName The name of the role to be created.
     */
    private void checkUniqueRole(String roleName){
        Optional<Role> exintingRole = roleRepository.findByName(roleName);
        if(exintingRole.isPresent()){
            throw new NotUniqueException("Role name already exists.");
        }
    }

}
