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

@Service
@RequiredArgsConstructor
public class SellerService implements ISeller {
    private final SellerRepository repository;
    private final IUser userService;
    private final IRole roleService;
    private final PasswordEncoder encoder;

    @Override
    public Seller findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seller not found."));
    }

    @Override
    public List<Seller> findAll() {
        return repository.findAll();
    }

    @Override
    public Seller save(SellerDTO sellerDTO) {
        Boolean user = userService.existsByUsername(sellerDTO.getUsername());
        if(user){
            throw new NotUniqueException("Email already taken.");
        }
        Role role = roleService.findByName("ROLE_SELLER");
        sellerDTO.setPassword(encoder.encode(sellerDTO.getPassword()));
        return repository.save(new Seller(sellerDTO, Set.of(role)));
    }
}
