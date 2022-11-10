package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.repository.AnnouncementRepository;
import br.com.dh.meli.desafiofinal.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.getAnnouncement;
import static br.com.dh.meli.desafiofinal.utils.TestUtils.getCategory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ICategory categoryService;

    @BeforeEach
    void setup() {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void findById_returnCategory_whenCategoryExists() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(getCategory()));

        Category category = categoryService.findById(1L);

        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(getCategory().getId());
        assertThat(category.getName()).isEqualTo(getCategory().getName());
        assertThat(category.getTemperature()).isEqualTo(getCategory().getTemperature());
    }
}