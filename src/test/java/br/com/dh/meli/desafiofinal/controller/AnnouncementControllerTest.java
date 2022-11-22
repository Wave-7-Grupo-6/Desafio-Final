package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.AnnouncementDTO;
import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.UserRepository;
import br.com.dh.meli.desafiofinal.security.JwtTokenFilter;
import br.com.dh.meli.desafiofinal.security.JwtTokenUtil;
import br.com.dh.meli.desafiofinal.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
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

@WebMvcTest(controllers = AnnouncementController.class)
class AnnouncementControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IAnnouncement announcementService;

    @MockBean
    private ISeller sellerService;

    @MockBean
    private ICategory categoryService;

    @MockBean
    private IProductType productTypeService;

    @MockBean
    private IBatch batchService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @WithMockUser(roles={"SELLER","ADMIN"})
    void save_returnAnnouncementAndCreatedStatus_whenAnnouncementValid() throws Exception {
        Announcement announcement = getAnnouncement();
        when(sellerService.findById(anyLong())).thenReturn(getSeller());
        when(categoryService.findById(anyLong())).thenReturn(getCategory());
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());
        when(announcementService.save(any())).thenReturn(getAnnouncement());

        AnnouncementDTO announcementDTO = new AnnouncementDTO(announcement);

        ResultActions resultActions = mockMvc.perform(
                        post("/api/v1/fresh-products")
                                .content(mapper.writeValueAsString(announcementDTO))
                                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(announcement.getId().intValue())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(announcement.getDescription())));
    }

    @Test
    void findById_returnAnnouncementAndSuccess_whenAnnouncementExists() throws Exception {
        Announcement announcement = getAnnouncement();
        when(announcementService.findById(anyLong())).thenReturn(announcement);
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());

        mockMvc.perform(
                        get("/api/v1/fresh-products/{id}", announcement.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(announcement.getId().intValue())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(announcement.getDescription())));
    }

    @Test
    void findById_ThrowsNotFoundException_whenAnnouncementDoesntExists() throws Exception {
        Announcement announcement = getAnnouncement();
        when(announcementService.findById(anyLong())).thenThrow(NotFoundException.class);
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());

        mockMvc.perform(
                        get("/api/v1/fresh-products/{id}", announcement.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_ThrowsNotFoundException_whenAnnouncementDoesntExists() throws Exception {
        when(announcementService.findAll()).thenReturn(Collections.emptyList());
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());

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
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());

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
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());

        String category = "Category 1";

        mockMvc.perform(
                        get("/api/v1/fresh-products/list?category={category}", category)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void findStockByAnnouncement_Id_returnAnnouncementStockDTO_whenSuccess() throws Exception {
        Announcement announcement = getAnnouncement();
        announcement.setBatchs(List.of(getBatch()));

        when(announcementService.findById(anyLong())).thenReturn(announcement);
        when(productTypeService.findById(anyLong())).thenReturn(getProductType());

        mockMvc.perform(
                        get("/api/v1/fresh-products/list?announcementId={announcementId}", announcement.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.section.id", CoreMatchers.is(announcement.getBatchs().get(0).getSection().getId().intValue())))
                .andExpect(jsonPath("$.section.warehouseId", CoreMatchers.is(announcement.getBatchs().get(0).getSection().getWarehouse().getId().intValue())))
                .andExpect(jsonPath("$.announcementId", CoreMatchers.is(announcement.getId().intValue())))
                .andExpect(jsonPath("$.batchList[0].id", CoreMatchers.is(announcement.getBatchs().get(0).getBatchNumber().intValue())))
                .andExpect(jsonPath("$.batchList[0].productQuantity", CoreMatchers.is(announcement.getBatchs().get(0).getProductQuantity())))
                .andExpect(jsonPath("$.batchList[0].dueDate", CoreMatchers.is(announcement.getBatchs().get(0).getDueDate().toString())));
    }

    @Test
    void findStockByAnnouncement_IdAndOrderBy_returnCorrectAnnouncementStockDTO_whenSuccess() throws Exception {
        // Test ordering by Id.
        Announcement announcement = getAnnouncement();
        ArrayList<Batch> batchArray = new ArrayList<Batch>(Arrays.asList(getBatch(), getLowIdBatch()));
        announcement.setBatchs(batchArray);

        when(announcementService.findById(anyLong())).thenReturn(announcement);

        mockMvc.perform(
                        get("/api/v1/fresh-products/list?announcementId={announcementId}&orderBy=L", announcement.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.section.id", CoreMatchers.is(announcement.getBatchs().get(0).getSection().getId().intValue())))
                .andExpect(jsonPath("$.section.warehouseId", CoreMatchers.is(announcement.getBatchs().get(0).getSection().getWarehouse().getId().intValue())))
                .andExpect(jsonPath("$.announcementId", CoreMatchers.is(announcement.getId().intValue())))
                .andExpect(jsonPath("$.batchList[0].id", CoreMatchers.is(announcement.getBatchs().get(1).getBatchNumber().intValue())))
                .andExpect(jsonPath("$.batchList[0].productQuantity", CoreMatchers.is(announcement.getBatchs().get(1).getProductQuantity())))
                .andExpect(jsonPath("$.batchList[0].dueDate", CoreMatchers.is(announcement.getBatchs().get(1).getDueDate().toString())));
    }
}