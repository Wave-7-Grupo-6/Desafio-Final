package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRole{

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        role.setId(null);
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        if(role.getId() == null || !roleRepository.existsById(role.getId())){
            throw new NotFoundException("Role id not provided or not found.");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElseThrow(() -> new NotFoundException("Role not found."));
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = roleRepository.findAll();
        if(roleList.isEmpty()){
            throw new NotFoundException("No Role found");
        }
        return roleList;
    }


}
