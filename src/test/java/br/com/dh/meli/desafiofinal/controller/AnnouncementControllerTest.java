package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.AnnouncementDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.ICategory;
import br.com.dh.meli.desafiofinal.service.ISeller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnnouncementController.class)
class AnnouncementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IAnnouncement announcementService;

    @MockBean
    private ISeller sellerService;

    @MockBean
    private ICategory categoryService;

    @Test
    void save_returnAnnouncementAndCreatedStatus_whenAnnouncementValid() throws Exception {
        Announcement announcement = getAnnouncement();
        when(sellerService.findById(anyLong())).thenReturn(getSeller());
        when(categoryService.findById(anyLong())).thenReturn(getCategory());
        when(announcementService.save(any())).thenReturn(getAnnouncement());

        AnnouncementDTO announcementDTO = new AnnouncementDTO(announcement);

        ResultActions resultActions = mockMvc.perform(
                        post("/api/v1/fresh-products")
                                .content(mapper.writeValueAsString(announcementDTO))
                                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(announcement.getId().intValue())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(announcement.getDescription())))
                .andExpect(jsonPath("$.price", CoreMatchers.is(announcement.getPrice().intValue())));
    }

    @Test
    void findById_returnAnnouncementAndSuccess_whenAnnouncementExists() throws Exception {
        Announcement announcement = getAnnouncement();
        when(announcementService.findById(anyLong())).thenReturn(announcement);

        mockMvc.perform(
                        get("/api/v1/fresh-products/{id}", announcement.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(announcement.getId().intValue())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(announcement.getDescription())))
                .andExpect(jsonPath("$.price", CoreMatchers.is(announcement.getPrice().intValue())));
    }

    @Test
    void findById_ThrowsNotFoundException_whenAnnouncementDoesntExists() throws Exception {
        Announcement announcement = getAnnouncement();
        when(announcementService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(
                        get("/api/v1/fresh-products/{id}", announcement.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_ThrowsNotFoundException_whenAnnouncementDoesntExists() throws Exception {
        when(announcementService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                        get("/api/v1/fresh-products")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_returnAnnouncementList_whenSuccess() throws Exception {
        Announcement announcement = getAnnouncement();
        when(announcementService.findAll()).thenReturn(List.of(announcement));

        mockMvc.perform(
                        get("/api/v1/fresh-products")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void findByCategory_returnAnnouncementList_whenSuccess() throws Exception {
        Announcement announcement = getAnnouncement();
        when(announcementService.findByCategory(anyString())).thenReturn(List.of(announcement));

        String category = "Category 1";

        mockMvc.perform(
                        get("/api/v1/fresh-products/list?queryType={category}", category)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}