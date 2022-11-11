package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.repository.SectionRepo;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @Mock
    private SectionRepo repository;

    private ISection service;

    @BeforeEach
    void setup() {
        service = new SectionService(repository);
    }

    @Test
    void findById_returnsSection_whenSuccess() {
        Section section = getSection();

        when(repository.findById(anyLong())).thenReturn(Optional.of(section));

        Section sectionFound = service.findById(1L);

        assertThat(sectionFound).isEqualTo(section);
    }
}
