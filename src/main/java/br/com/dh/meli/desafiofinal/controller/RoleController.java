package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.service.IRole;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Role controller.
 */
@RestController
@RequestMapping("/api/v1/role")
@PreAuthorize("hasRole('ADMIN')")
@Api(tags = "Role Controller", value = "RoleController", description = "Controller for Role")
public class RoleController {

    @Autowired
    private IRole roleService;

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<Role>> findAll(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id){
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    /**
     * Save response entity.
     *
     * @param role the role
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role role){
        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }

    /**
     * Update response entity.
     *
     * @param role the role
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity<Role> update(@RequestBody Role role){
        return new ResponseEntity<>(roleService.update(role), HttpStatus.CREATED);
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteById(@PathVariable Long id){
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
