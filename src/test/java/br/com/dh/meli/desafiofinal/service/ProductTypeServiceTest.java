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
        Optional<ProductType> productType = Optional.of(getProductType());
        when(repo.findById(anyLong())).thenReturn(productType);

        ProductType productTypeFound = service.findById(1L);

        assertThat(productTypeFound).isNotNull();
        assertThat(productTypeFound.getId()).isEqualTo(productType.get().getId());
        assertThat(productTypeFound.getAnnouncements()).isEqualTo(productType.get().getAnnouncements());
    }
}
