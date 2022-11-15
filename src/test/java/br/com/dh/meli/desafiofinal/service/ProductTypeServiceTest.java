package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.ProductType;
import br.com.dh.meli.desafiofinal.repository.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceTest {

    @Mock
    ProductTypeRepository repo;

    ProductTypeService service;

    @BeforeEach
    void setUp() {
        service = new ProductTypeService(repo);
    }

    @Test
    void findById_returnProductType_whenProductTypeExists() {
        when(repo.findById(anyLong())).thenReturn(Optional.of(getProductType()));

        ProductType productType = service.findById(1L);

        assertThat(productType).isNotNull();
        assertThat(productType.getId()).isEqualTo(getProductType().getId());
    }
}
