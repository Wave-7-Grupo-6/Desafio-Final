package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.exceptions.NotUniqueException;
import br.com.dh.meli.desafiofinal.model.Role;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import br.com.dh.meli.desafiofinal.service.IRole;
import br.com.dh.meli.desafiofinal.service.ISeller;
import br.com.dh.meli.desafiofinal.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import br.com.dh.meli.desafiofinal.service.ISeller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Seller service.
 */
@Service
@RequiredArgsConstructor
public class SellerService implements ISeller {
    private final SellerRepository repository;
    private final IUser userService;
    private final IRole roleService;
    private final PasswordEncoder encoder;


    /**
     * If the seller exists, return it, otherwise throw an exception.
     *
     * @param id The id of the seller you want to find.
     * @return A Seller object.
     */
    @Override
    public Seller findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seller not found."));
    }

    /**
     * Find all sellers and return them as a list.
     *
     * @return A list of all sellers in the database.
     */
    @Override
    public List<Seller> findAll() {
        return repository.findAll();
    }

    /**
     * Save a new seller to the database.
     *
     * @param sellerDTO This is the object that will be sent to the API.
     * @return A Seller object.
     */
    @Override
    public Seller save(SellerDTO sellerDTO) {
        Boolean user = userService.existsByUsername(sellerDTO.getUsername());
        if (user) {
            throw new NotUniqueException("Email already taken.");
        }
        Role role = roleService.findByName("ROLE_SELLER");
        sellerDTO.setPassword(encoder.encode(sellerDTO.getPassword()));
        return repository.save(new Seller(sellerDTO, Set.of(role)));
    }
}
