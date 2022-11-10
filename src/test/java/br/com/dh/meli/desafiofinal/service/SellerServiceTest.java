package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.SellerDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getSeller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    private ISeller sellerService;

    @BeforeEach
    void setUp() {
        sellerService = new SellerService(sellerRepository);
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

        Seller savedSeller = sellerService.save(new SellerDTO(seller));

        assertThat(savedSeller.getId()).isEqualTo(seller.getId());
        assertThat(savedSeller.getName()).isEqualTo(seller.getName());
    }
}