package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Charities;
import br.com.dh.meli.desafiofinal.repository.CharitiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getCharities;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CharitiesServiceTest {

    @Mock
    private CharitiesRepository repository;

    private ICharities charitiesService;

   @BeforeEach
   void setUp() { charitiesService = new CharitiesService(repository); }

    @Test
    void save_returnCharity_whenValid() {
        Charities charities = getCharities();
        when(repository.save(charities)).thenReturn(charities);

        Charities savedCharities = charitiesService.save(charities);

        assertThat(savedCharities).isNotNull();
        assertThat(savedCharities.getName()).isEqualTo(charities.getName());
        assertThat(savedCharities.getCnpj()).isEqualTo(charities.getCnpj());
        assertThat(savedCharities.getEmail()).isEqualTo(charities.getEmail());
    }

    @Test
    void findAll_returnListCharities_whenSuccess() {
        Charities charities = getCharities();
        when(repository.findAll()).thenReturn(List.of(charities));

        List<Charities> charitiesList = charitiesService.findAll();

        assertThat(charitiesList).isNotNull();
        assertThat(charitiesList).isNotEmpty();
        assertThat(charitiesList.size()).isEqualTo(1);
        assertThat(charitiesList.get(0).getName()).isEqualTo(charities.getName());
        assertThat(charitiesList.get(0).getCnpj()).isEqualTo(charities.getCnpj());
        assertThat(charitiesList.get(0).getEmail()).isEqualTo(charities.getEmail());
    }

    @Test
    void findById_returnCharity_whenCharityExist() {
        Charities charities = getCharities();
        when(repository.findById(anyLong())).thenReturn(Optional.of(charities));

        Charities charity = charitiesService.findById(1L);

        assertThat(charity).isNotNull();
        assertThat(charity.getId()).isEqualTo(charities.getId());
        assertThat(charity.getName()).isEqualTo(charities.getName());
        assertThat(charity.getCnpj()).isEqualTo(charities.getCnpj());
        assertThat(charity.getEmail()).isEqualTo(charities.getEmail());
    }

    @Test
    void update_returnCharity_whenCharityExist() {
        Charities charities = getCharities();
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(charities)).thenReturn(charities);

        Charities charity = charitiesService.update(1L, charities);

        assertThat(charity).isNotNull();
        assertThat(charity.getId()).isEqualTo(charities.getId());
        assertThat(charity.getName()).isEqualTo(charities.getName());
        assertThat(charity.getCnpj()).isEqualTo(charities.getCnpj());
        assertThat(charity.getEmail()).isEqualTo(charities.getEmail());
    }

    @Test
    void update_throwException_whenCharityNotExist() {
        Charities charities = getCharities();
        when(repository.existsById(anyLong())).thenReturn(false);

        assertThat(charitiesService.update(1L, charities)).isNull();
    }

    @Test
    void deleteById_returnTrue_whenCharityExist() {
        when(repository.existsById(anyLong())).thenReturn(true);

        boolean deleted = charitiesService.deleteById(1L);

        assertThat(deleted).isTrue();
    }

    @Test
    void deleteById_returnFalse_whenCharityNotExist() {
        when(repository.existsById(anyLong())).thenReturn(false);

        boolean deleted = charitiesService.deleteById(1L);

        assertThat(deleted).isFalse();
    }
}
