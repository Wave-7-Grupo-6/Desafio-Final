package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.repository.AnnouncementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class AnnouncementServiceTest {

    @Mock
    private AnnouncementRepository announcementRepository;

    @Mock
    private ICategory categoryService;

    private IAnnouncement announcementService;

    @BeforeEach
    void setUp() {
        announcementService = new AnnouncementService(announcementRepository, categoryService);
    }

    @Test
    void findById_returnAnnouncement_whenAnnouncementExists() {
        when(announcementRepository.findById(anyLong())).thenReturn(Optional.of(getAnnouncement()));

        Announcement announcement = announcementService.findById(1L);

        assertThat(announcement).isNotNull();
        assertThat(announcement.getId()).isEqualTo(getAnnouncement().getId());
        assertThat(announcement.getDescription()).isEqualTo(getAnnouncement().getDescription());
    }

    @Test
    void findById_ThrowsNotFoundException_whenAnnouncementDoesntExists() {
        when(announcementRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()-> announcementService.findById(1L));
    }

    @Test
    void save_returnAnnouncement_whenSuccess() {
        Announcement announcement = getAnnouncement();
        when(announcementRepository.save(announcement)).thenReturn(announcement);

        Announcement savedAnnouncement = announcementService.save(announcement);

        assertThat(savedAnnouncement.getId()).isEqualTo(getAnnouncement().getId());
        assertThat(savedAnnouncement.getDescription()).isEqualTo(getAnnouncement().getDescription());
    }

    @Test
    void findAll_returnAnnouncementList_whenSuccess() {
        when(announcementRepository.findAll()).thenReturn(List.of(getAnnouncement()));

        List<Announcement> announcementList = announcementService.findAll();
        assertThat(announcementList).isNotEmpty();
        assertThat(announcementList.size()).isEqualTo(1);
    }

    @Test
    void findByCategory_returnAnnouncementList_whenSuccess() {
        when(announcementRepository.findByCategory_Id(anyLong())).thenReturn(List.of(getAnnouncement()));
        when(categoryService.findByName(anyString())).thenReturn(getCategory());

        List<Announcement> announcementList = announcementService.findByCategory("Category 1");
        assertThat(announcementList).isNotEmpty();
        assertThat(announcementList.size()).isEqualTo(1);
    }

    @Test
    void findByCategory_ThrowsNotFoundException_whenAnnouncementDoesntExists() {
        when(announcementRepository.findByCategory_Id(anyLong())).thenReturn(Collections.emptyList());
        when(categoryService.findByName(anyString())).thenReturn(getCategory());

        assertThrows(NotFoundException.class, ()-> announcementService.findByCategory("Category 1"));
    }

    @Test
    void findByProductType_returnAnnouncementList_whenSuccess() {
        when(announcementRepository.findByProductType_Id(1L)).thenReturn(List.of(getAnnouncement()));

        List<Announcement> announcementList = announcementService.findByProductType(1L);
        assertThat(announcementList).isNotEmpty();
        assertThat(announcementList.size()).isEqualTo(1);
    }
}