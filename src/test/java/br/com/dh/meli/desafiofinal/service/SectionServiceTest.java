package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.repository.SectionRepo;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
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
        assertThat(sectionFound.getName()).isEqualTo(section.getName());
        assertThat(sectionFound.getVolumeMax()).isEqualTo(section.getVolumeMax());
        assertThat(sectionFound.getVolumeOccupied()).isEqualTo(section.getVolumeOccupied());
        assertThat(sectionFound.getTemperature()).isEqualTo(section.getTemperature());
        assertThat(sectionFound.getCategory()).isEqualTo(section.getCategory());
        assertThat(sectionFound.getWarehouse()).isEqualTo(section.getWarehouse());
        assertThat(sectionFound.getInboundOrders()).isEqualTo(section.getInboundOrders());
        assertThat(sectionFound.getSeller()).isEqualTo(section.getSeller());
    }

    @Test
    void findById_throwsNoSuchElementException_whenReturnIsEmpty() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.findById(1L));
    }

    @Test
    void save_returnsSection_whenSuccess() {
        Section section = getSection();

        when(repository.save(any(Section.class))).thenReturn(section);

        Section sectionSaved = service.save(section);

        assertThat(sectionSaved).isEqualTo(section);
    }
}
