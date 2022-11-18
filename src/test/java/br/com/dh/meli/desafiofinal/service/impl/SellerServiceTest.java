package br.com.dh.meli.desafiofinal.service.impl;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import br.com.dh.meli.desafiofinal.service.IRole;
import br.com.dh.meli.desafiofinal.service.ISeller;
import br.com.dh.meli.desafiofinal.service.IUser;
import br.com.dh.meli.desafiofinal.service.impl.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getSeller;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getSellerRole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private IRole roleService;
    @Mock
    private IUser userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private ISeller sellerService;

    @BeforeEach
    void setUp() {
        sellerService = new SellerService(sellerRepository, userService, roleService, passwordEncoder);
    }

    @Test
    void findById_returnSeller_whenSellerExists() {
        Seller seller = getSeller();
        when(sellerRepository.findById(anyLong())).thenReturn(Optional.of(seller));

        Seller foundSeller = sellerService.findById(1L);

        assertThat(foundSeller.getId()).isEqualTo(seller.getId());
        assertThat(foundSeller.getName()).isEqualTo(seller.getName());

    }

    @Test
    void findById_ThrowsNotFoundException_whenSellerDoesntExists() {
        when(sellerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> sellerService.findById(1L));
    }

    @Test
    void findAll_returnSellerList_whenSellerExists() {
        Seller seller = getSeller();
        when(sellerRepository.findAll()).thenReturn(List.of(seller));

        List<Seller> sellerList = sellerService.findAll();

        assertThat(sellerList.size()).isEqualTo(1);
        assertThat(sellerList.get(0).getId()).isEqualTo(seller.getId());
        assertThat(sellerList.get(0).getName()).isEqualTo(seller.getName());
    }

    @Test
    void save_returnSuccess_whenSellerValid() {
        Seller seller = getSeller();
        when(sellerRepository.save(any(Seller.class))).thenReturn(seller);
        when(roleService.findByName("ROLE_SELLER")).thenReturn(getSellerRole());
        when(userService.existsByUsername(anyString())).thenReturn(false);

        Seller savedSeller = sellerService.save(new SellerDTO(seller));

        assertThat(savedSeller.getId()).isEqualTo(seller.getId());
        assertThat(savedSeller.getName()).isEqualTo(seller.getName());
    }
}